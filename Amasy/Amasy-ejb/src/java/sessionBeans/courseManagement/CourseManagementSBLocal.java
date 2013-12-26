/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.courseManagement;

import DTOs.AnswerDTO;
import DTOs.BlockClassDTO;
import DTOs.BlockClassListDTO;
import DTOs.CourseDTO;
import DTOs.DayBlockClassListDTO;
import DTOs.ListCourseDTO;
import DTOs.TimeBlockClassListDTO;
import DTOs.ListUserDTO;
import java.util.LinkedList;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface CourseManagementSBLocal {

    ListCourseDTO getAllCourse();

    AnswerDTO insertNewCourse(CourseDTO userDTO);

    CourseDTO getCourseByName(String courseName);

    ListCourseDTO getAllCoursesOfTeacher(Long idUser);

    CourseDTO getCourseById(Long courseId);

    AnswerDTO allocateBlockclassesoToCourse(Long idCourse, BlockClassDTO blockClassDTO);

    AnswerDTO updateCourse(CourseDTO courseDTO, Long courseId);

    TimeBlockClassListDTO getAllTimeBlockClass();

    DayBlockClassListDTO getAllDayBlockClassDTO();

    BlockClassListDTO getAllBlockClassOfCourse(Long idCourse);

    AnswerDTO configureAssistanceTimebox(CourseDTO course);

    ListUserDTO getAllStudentsFromCourse(Long idCourse);    
}
