/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.User;
import javax.ejb.Stateless;
import com.digitalpersona.uareu.*;

/**
 *
 * @author Pingeso
 */
@Stateless
public class FingerprintManagementSB implements FingerprintManagementSBLocal {

    
    
    @Override
    public User validateFingerprintBM() {        
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
