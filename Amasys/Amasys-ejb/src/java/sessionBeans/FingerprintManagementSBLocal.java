/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.User;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface FingerprintManagementSBLocal {

    User validateFingerprintBM();
    
}
