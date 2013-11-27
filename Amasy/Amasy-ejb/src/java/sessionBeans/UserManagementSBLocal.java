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

    public void newUser(Long id, String firstName, String lastName, String email, String homePhone, String cellPhone, int rut, String userTypeName);

   public void getAllUser();

    public User findUserByRut(int rut);

    String findUserTypeByUserName(String userName);
     
    
}
