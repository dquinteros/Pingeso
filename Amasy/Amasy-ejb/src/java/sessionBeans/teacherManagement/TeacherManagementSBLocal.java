/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.teacherManagement;

import DTOs.AnswerDTO;
import DTOs.NewUserDTO;
import DTOs.UserDTO;
import java.util.LinkedList;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface TeacherManagementSBLocal {

    /**
     *
     * @return
     */
    LinkedList<UserDTO> getAllTeacher();
    
    /**
     *
     * @param userDTO
     * @return
     */
    AnswerDTO insertNewTeacher(NewUserDTO userDTO);
    
    /**
     *
     * @param userId
     * @return
     */
    NewUserDTO getTeacherById(long userId);
    
    /**
     *
     * @param newTeacher
     * @param studentId
     * @return
     */
    AnswerDTO updateTeacher(NewUserDTO newTeacher, Long studentId);
    
    /**
     *
     * @param id
     * @return
     */
    AnswerDTO deleteTeacher(Long id);
    
}
