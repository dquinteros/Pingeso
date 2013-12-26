/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.studentManagement;

import DTOs.AnswerDTO;
import DTOs.AssistanceListDTO;
import DTOs.ListCourseDTO;
import DTOs.NewUserDTO;
import DTOs.UserDTO;
import java.util.Date;
import java.util.LinkedList;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface StudentManagementSBLocal {

    /**
     *
     * @return
     */
    LinkedList<UserDTO> getAllStudent();

    /**
     *
     * @param user
     * @param enrollYear
     * @return
     */
    AnswerDTO insertNewStudent(NewUserDTO user, Date enrollYear);

    /**
     *
     * @param newStudent
     * @param studentId
     * @return
     */
    AnswerDTO updateStudent(NewUserDTO newStudent, Long studentId);

    /**
     *
     * @param userId
     * @return
     */
    NewUserDTO getStudentById(long userId);

    /**
     *
     * @param id
     * @return
     */
    AnswerDTO deleteStudent(Long id);

    /**
     *
     * @param idUser
     * @param idCourse
     * @return
     */
    AnswerDTO enrollStudentOnCourse(Long idUser, Long idCourse);

    /**
     *
     * @param idUser
     * @return
     */
    ListCourseDTO getCoursesFromStudent(Long idUser);

    AssistanceListDTO getAssistanceStudent(Long idCourse, Long idUser);
}
