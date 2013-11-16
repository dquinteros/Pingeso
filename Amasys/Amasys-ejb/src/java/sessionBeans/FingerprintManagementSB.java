/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.User;
import javax.ejb.Stateless;
import com.digitalpersona.uareu.*;
import java.util.Iterator;
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
    @PersistenceContext(unitName = "Amasys-ejbPU")
    private EntityManager em;

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public User validateFingerprintBM() {
        
        Fmd fmd1 = null;int view_index1 = 0;Fmd[] fmds = null;int threshold_score = 0;int candidates_requested = 0;
        
        Engine eng = UareUGlobal.GetEngine();        
        try {
            eng.Identify(fmd1, view_index1, fmds,threshold_score,candidates_requested);
        } catch (UareUException ex) {
            Logger.getLogger(FingerprintManagementSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }    
    
    @Override
    public List<Fmd> selectAllFingerprints() {
        Query q = this.em.createNamedQuery("User.selectAllFingerprint");
        List<byte[]> r = q.getResultList();
        if(r.isEmpty()){
            return null;
        }else{
          List<Fmd> fmds = null;
          for (byte[] s : r){
              Fmd f;
              try {
                  f = UareUGlobal.GetImporter().ImportFmd(s, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
                   fmds.add(f);
              } catch (UareUException ex) {
                  Logger.getLogger(FingerprintManagementSB.class.getName()).log(Level.SEVERE, null, ex);
              }
             
          }
          return fmds; 
        }
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
    
}
