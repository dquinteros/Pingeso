/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.courseManagement;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import DTOs.ListCourseDTO;
import DTOs.ListUserDTO;
import entity.BlockClass;
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
    AnswerDTO insertNewCourse(CourseDTO userDTO);

    CourseDTO getCourseByName(String courseName);

    ListCourseDTO getAllCoursesOfTeacher(Long idUser);

    CourseDTO getCourseById(Long courseId);

    AnswerDTO allocateBlockclassesoToCourse(Long idCourse, LinkedList<BlockClass> listBlockClass);

    AnswerDTO updateCourse(CourseDTO courseDTO, Long courseId);
    
    AnswerDTO configureAssistanceTimebox(CourseDTO course);
    
    ListUserDTO getAllStudentsFromCourse(Long idCourse);
}
