/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.universityManagement;

import DTOs.ListUniversityDTO;
import DTOs.UserDTO;
import java.util.LinkedList;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface UniversityManagementSBLocal {
    
    ListUniversityDTO getAllUniversity();
    
}
