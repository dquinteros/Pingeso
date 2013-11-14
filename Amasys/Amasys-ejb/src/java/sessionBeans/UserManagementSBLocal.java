/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.User;
import entity.UserType;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface UserManagementSBLocal {

    void newUser(Long id, String firstName, String lastName, String email, String homePhone, String cellPhone, int rut, String userTypeName);

    User findUserRut(int rut);

    void getAllUser();
    
}
