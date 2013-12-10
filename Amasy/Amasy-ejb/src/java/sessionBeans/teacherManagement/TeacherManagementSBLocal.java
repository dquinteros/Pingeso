/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.teacherManagement;

import DTOs.AnswerDTO;
import DTOs.NewUserDTO;
import DTOs.UserDTO;
import entity.User;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface TeacherManagementSBLocal {

    LinkedList<UserDTO> getAllTeacher();
    
    AnswerDTO insertNewTeacher(NewUserDTO userDTO);
    
    NewUserDTO getTeacherById(long userId);
    
    AnswerDTO updateTeacher(NewUserDTO newTeacher, Long studentId);
    
    AnswerDTO deleteTeacher(Long id);
    
}
