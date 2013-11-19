/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import javax.ejb.Stateless;
import com.digitalpersona.uareu.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pingeso
 */
@Stateless
public class FingerprintManagementSB implements FingerprintManagementSBLocal {
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @PersistenceContext(unitName = "Amasys-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    /**
     * Validating that a Fmd exists in the database
     *
     * @param fmd1 The ANSI format Fmd value of the fingerprint
     * @return The value log Fmd fingerprint in the database
     */
    @Override
    public Fmd validateFingerprintBM(Fmd fmd1) {
        int view_index1 = 0;
        Fmd[] fmds = this.selectAllFingerprints();
        int threshold_score = Engine.PROBABILITY_ONE / 10000;
        int candidates_requested = 1;

        Engine eng = UareUGlobal.GetEngine();
        Fmd r = null;
        try {
            Engine.Candidate[] ec = eng.Identify(fmd1, view_index1, fmds, threshold_score, candidates_requested);
            r = fmds[ec[0].fmd_index];
        } catch (UareUException ex) {
            Logger.getLogger(FingerprintManagementSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    /**
     * Get every fingerprint record from the users in the DB
     *
     * @return All fingerprint records
     */
    @Override
    public Fmd[] selectAllFingerprints() {
        Query q = this.em.createNamedQuery("User.selectAllFingerprint");
        List<String> r = q.getResultList();
        if (r.isEmpty()) {
            return null;
        } else {
            Fmd[] fmds = new Fmd[r.size() + 1];
            int i = 0;
            for (String s : r) {
                try {
                    Importer imp = UareUGlobal.GetImporter();
                    fmds[i] = imp.ImportFmd(this.hexStringToByteArray(s), Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
                } catch (UareUException ex) {
                    Logger.getLogger(FingerprintManagementSB.class.getName()).log(Level.SEVERE, null, ex);
                }
                i++;
            }
            return fmds;
        }
    }

    /**
     * Convert string in to byte array
     *
     * @param s
     * @return value in byte[] of the string
     */
    @Override
    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
