/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.studentManagement;

import DTOs.AnswerDTO;
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

    LinkedList<UserDTO> getAllStudent();

    AnswerDTO insertNewStudent(NewUserDTO user, Date enrollYear);

    AnswerDTO updateStudent(NewUserDTO newStudent, Long studentId);

    NewUserDTO getStudentById(long userId);

    AnswerDTO deleteStudent(Long id);

    AnswerDTO enrollStudentOnCourse(Long idUser, Long idCourse);

    ListCourseDTO getCoursesFromStudent(Long idUser);
    
}
