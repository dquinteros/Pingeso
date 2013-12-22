/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.courseManagement;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import java.util.LinkedList;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface CourseManagementSBLocal {
        /**
     *
     * @return
     */
    public LinkedList<CourseDTO> getAllCourse();
        /**
     *
     * @param userDTO
     * @return
     */
    public AnswerDTO insertNewCourse(CourseDTO userDTO);
        
    
}
