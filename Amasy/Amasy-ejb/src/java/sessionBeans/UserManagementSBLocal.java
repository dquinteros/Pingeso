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
    public UserDTO findUserByRut(int rut);

    public UserDTO findUserByUserName(String username);
}
