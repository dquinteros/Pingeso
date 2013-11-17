/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import javax.ejb.Local;
import com.digitalpersona.uareu.*;

/**
 *
 * @author Pingeso
 */
@Local
public interface FingerprintManagementSBLocal {

    Fmd validateFingerprintBM(Fmd fmd1);

    Fmd[] selectAllFingerprints();

    byte[] hexStringToByteArray(String s);
    
}
