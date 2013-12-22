/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.courseManagement;

import DTOs.CourseDTO;
import java.util.LinkedList;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface CourseManagementSBLocal {
        public LinkedList<CourseDTO> getAllCourse();
        
    
}
