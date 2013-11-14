/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.User;
import javax.ejb.Stateless;
import com.digitalpersona.uareu.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pingeso
 */
@Stateless
public class FingerprintManagementSB implements FingerprintManagementSBLocal {

    
    
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
