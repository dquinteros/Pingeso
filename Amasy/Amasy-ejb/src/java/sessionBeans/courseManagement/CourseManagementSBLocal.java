/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.courseManagement;

import DTOs.AnswerDTO;
import DTOs.AssistanceListCourseDTO;
import DTOs.BlockClassDTO;
import DTOs.BlockClassListDTO;
import DTOs.CourseDTO;
import DTOs.DayBlockClassListDTO;
import DTOs.ListCourseDTO;
import DTOs.ListGroupStudentPerCourseDTO;
import DTOs.TimeBlockClassListDTO;
import DTOs.ListUserDTO;
import DTOs.UserDTO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface CourseManagementSBLocal {

    ListCourseDTO getAllCourse();

    AnswerDTO insertNewCourse(CourseDTO courseDTO, Long idUser, Long idUniversity);

    CourseDTO getCourseByName(String courseName);

    ListCourseDTO getAllCoursesOfTeacher(Long idUser);

    CourseDTO getCourseById(Long courseId);

    AnswerDTO allocateBlockclassesoToCourse(Long idCourse, BlockClassDTO blockClassDTO);

    AnswerDTO updateCourse(CourseDTO courseDTO, Long courseId, Long idUniversity, Long idUser);

    TimeBlockClassListDTO getAllTimeBlockClass();

    DayBlockClassListDTO getAllDayBlockClassDTO();

    BlockClassListDTO getAllBlockClassOfCourse(Long idCourse);

    AnswerDTO configureAssistanceTimebox(CourseDTO course);

    ListUserDTO getAllStudentsFromCourse(Long idCourse);    

    AssistanceListCourseDTO assistanceListCourse(Long idCourse);
    
    AnswerDTO createGroup(Long idCourse, String groupName);
    
    ListGroupStudentPerCourseDTO getAllGroupsOfCourse(Long idCourse);
    
    AnswerDTO deleteGroup(String groupName, Long idCourse);
    
    ListUserDTO getAllStudentsWithGroup(Long idCourse);
    
    ListUserDTO getAllStudentsWithoutGroup(Long idCourse);
    
    List<UserDTO> getStudentsOfGroup(String groupName, Long idCourse);
    
    AnswerDTO updateGroup(List<Long> ListIdUser, String groupName, Long idCourse);
    
    ListCourseDTO getAllCourseOfStudentInUniversity(Long idUser);
    
    ListCourseDTO getAllCoursesOfStudent(Long idUser);
    
}
