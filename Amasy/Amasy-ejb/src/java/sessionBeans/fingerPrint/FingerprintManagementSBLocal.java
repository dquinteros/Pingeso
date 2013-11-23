/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.fingerPrint;

import javax.ejb.Local;
import com.digitalpersona.uareu.*;
import entity.BlockClass;
import entity.User;

/**
 *
 * @author Pingeso
 */
@Local
public interface FingerprintManagementSBLocal {
    
    public Fmd[] selectAllFingerprints();

    public byte[] hexStringToByteArray(String s);

    public Fmd stringToFmd(String s);

    public String fmdToString(Fmd f);

    public String byteArrayToHexString(byte[] ba);

    public User validateFingerprintBM(String s, BlockClass blockClass);

    public User findUserByFingerprint(String fingerPrint);
}
