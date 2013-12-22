/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DTOs.UserDTO;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface UserManagementSBLocal {
    /**
     *
     * @param rut
     * @return
     */
    public UserDTO findUserByRut(int rut);

    /**
     *
     * @param username
     * @return
     */
    public UserDTO findUserByUserName(String username);
}
