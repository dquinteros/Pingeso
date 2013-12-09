/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.studentManagement;

import DTOs.AnswerDTO;
import DTOs.NewUserDTO;
import DTOs.UserDTO;
import DTOs.UserListDTO;
import entity.Student;
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

    public AnswerDTO insertNewStudent(NewUserDTO user, Date enrollYear);

//    public UserListDTO getUsersPerTable(String rut, String firstName, String lastName, int page, int studentsPerPage);
    public LinkedList<UserDTO> getListStudent(String rut, String firstName, String lastName, int page, int studentsPerPage);

    public AnswerDTO updateStudent(NewUserDTO newStudent, Long studentId);

    public NewUserDTO getStudentById(long userId);

    public AnswerDTO deleteStudent(Long id);
}
