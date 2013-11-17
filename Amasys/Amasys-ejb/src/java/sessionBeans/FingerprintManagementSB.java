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

    @Override
    public Fmd[] selectAllFingerprints() {
        Query q = this.em.createNamedQuery("User.selectAllFingerprint");
        List<byte[]> r = q.getResultList();
        if (r.isEmpty()) {
            return null;
        } else {
            Fmd[] fmds = new Fmd[r.size() + 1];
            int i = 0;
            for (byte[] s : r) {
                try {
                    Fmd f;
                    f = UareUGlobal.GetImporter().ImportFmd(s, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
                    fmds[i] = f;
                } catch (UareUException ex) {
                    Logger.getLogger(FingerprintManagementSB.class.getName()).log(Level.SEVERE, null, ex);
                }
                i++;
            }
            return fmds;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
