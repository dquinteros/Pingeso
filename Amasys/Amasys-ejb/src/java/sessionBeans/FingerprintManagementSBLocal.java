/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.User;
import javax.ejb.Local;
import com.digitalpersona.uareu.*;
import java.util.List;

/**
 *
 * @author Pingeso
 */
@Local
public interface FingerprintManagementSBLocal {

    User validateFingerprintBM(Fmd fmd1);

    Fmd[] selectAllFingerprints();
    
}
