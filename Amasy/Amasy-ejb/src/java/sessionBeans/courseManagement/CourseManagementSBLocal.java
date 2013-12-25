/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.courseManagement;

import DTOs.AnswerDTO;
import DTOs.BlockClassDTO;
import DTOs.BlockClassListDTO;
import DTOs.CourseDTO;
import DTOs.DayBlockClassDTO;
import DTOs.DayBlockClassListDTO;
import DTOs.ListCourseDTO;
import DTOs.TimeBlockClassListDTO;
import entity.BlockClass;
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

    AnswerDTO allocateBlockclassesoToCourse(Long idCourse, LinkedList<BlockClassDTO> listBlockClass);

    AnswerDTO updateCourse(CourseDTO courseDTO, Long courseId);

    TimeBlockClassListDTO getAllTimeBlockClass();

    DayBlockClassListDTO getAllDayBlockClassDTO();

    BlockClassListDTO getAllBlockClassOfCourse(Long idCourse);
}
