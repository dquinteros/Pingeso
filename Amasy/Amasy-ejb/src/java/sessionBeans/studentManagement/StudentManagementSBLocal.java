/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.studentManagement;

import DTOs.AnswerDTO;
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

    public AnswerDTO insertNewStudent(NewUserDTO user, Date enrollYear);

    public AnswerDTO deleteStudent(int id);
}
