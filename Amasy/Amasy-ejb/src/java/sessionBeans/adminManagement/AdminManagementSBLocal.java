/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.adminManagement;

import DTOs.UserDTO;
import java.util.LinkedList;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface AdminManagementSBLocal {
    
    public LinkedList<UserDTO> getAllAdmin();
    
}
