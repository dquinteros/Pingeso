/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.fingerPrint;

import javax.ejb.Stateless;
import com.digitalpersona.uareu.*;
import entity.Assistance;
import entity.BlockClass;
import entity.Student;
import entity.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pingeso
 */
@Stateless
public class FingerprintManagementSB implements FingerprintManagementSBLocal {

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
    public User validateFingerprintBM(String s, BlockClass blockClass) {

        Fmd fmd1 = this.stringToFmd(s);
        int view_index1 = 0;
        Fmd[] fmds = this.selectAllFingerprints();
        int threshold_score = Engine.PROBABILITY_ONE / 10000;
        int candidates_requested = 1;
        Fmd r = null;

        try {
            Engine.Candidate[] ec = UareUGlobal.GetEngine().Identify(fmd1, view_index1, fmds, threshold_score, candidates_requested);
            r = fmds[ec[0].fmd_index];
        } catch (UareUException ex) {
            Logger.getLogger(FingerprintManagementSB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Se cae aqui!!!!!");
            return null;
        }

        String sf = this.fmdToString(r);

        User u = this.findUserByFingerprint(sf);
        if(u!=null){
            Student student = findStudentByIdUser(u.getId());
            
            
            
            em.getTransaction().begin();            
            Assistance newAssistance = new Assistance();
            newAssistance.setStudent(student);
            newAssistance.setDate(null);
            newAssistance.setBlockClass(blockClass);
            em.persist(newAssistance);
            em.getTransaction().commit();            
        }
        return u;
    }

    private Student findStudentByIdUser(Long idUser){
        Query q = this.em.createNamedQuery("Student.findByIdUser", Student.class);
        q.setParameter("idUser", idUser);
        Student res = null;
        try {
            res = (Student)q.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        return res;        
    }
    
    
    
    @Override
    public User findUserByFingerprint(String fingerPrint) {
        Query q = this.em.createNamedQuery("User.findFingerprint", User.class);
        q.setParameter("fingerPrint", fingerPrint);
        User res = null;
        try {
            res = (User) q.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        if(res == null){
            System.out.println("No hay usuario");
        }
        return res;
    }

    /**
     * Get every fingerprint record from the users in the DB
     *
     * @return All fingerprint records
     */
    @Override
    public Fmd[] selectAllFingerprints() {
        Query q = em.createNamedQuery("User.selectAllFingerprint", User.class);
        List<String> r = q.getResultList();
        if (r.isEmpty()) {
            return null;
        } else {
            Fmd[] fmds = new Fmd[r.size() + 1];
            int i = 0;
            for (String s : r) {
                if (s != null) {
                    fmds[i] = this.stringToFmd(s);
                    i++;
                }
            }
            if(fmds == null){
                System.out.println("No encontró huellas");
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
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    @Override
    public String fmdToString(Fmd f) {
        return this.byteArrayToHexString(f.getData());

    }

    @Override
    public String byteArrayToHexString(byte[] ba) {
        StringBuilder strBuilder = new StringBuilder();
        int ibyte;
        for (int i = 0; i < ba.length; i++) {
            ibyte = ba[i] & 0xFF;
            if (ibyte < 16) {
                strBuilder.append("0");
            }
            strBuilder.append(Integer.toHexString(ibyte));
        }
        return strBuilder.toString();
    }

    @Override
    public Fmd stringToFmd(String s) {
        byte[] b = this.hexStringToByteArray(s);
        Fmd f = null;
        try {
            f = UareUGlobal.GetImporter().ImportFmd(b, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
      } catch (UareUException ex) {
            Logger.getLogger(FingerprintManagementSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    
}
