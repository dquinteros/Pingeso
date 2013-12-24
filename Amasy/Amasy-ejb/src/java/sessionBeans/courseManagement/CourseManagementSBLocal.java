/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.courseManagement;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import DTOs.ListCourseDTO;
import DTOs.NewUserDTO;
import entity.Course;
import entity.User;
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
    public ListCourseDTO getAllCourse();
        /**
     *
     * @param userDTO
     * @return
     */
    public AnswerDTO insertNewCourse(CourseDTO userDTO);


    public CourseDTO getCourseByName(String courseName);

    
    
    public ListCourseDTO getAllCoursesOfTeacher(Long idUser);

    CourseDTO getCourseById(Long courseId);
        
    
}
