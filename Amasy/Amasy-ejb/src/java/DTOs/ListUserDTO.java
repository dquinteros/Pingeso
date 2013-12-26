/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class ListUserDTO {

    private Collection<UserDTO> listUser;
    private AnswerDTO answerDTO;
    
    public ListUserDTO(){
    }

    public ListUserDTO(Collection<UserDTO> listUser, AnswerDTO answerDTO) {
        this.listUser = listUser;
        this.answerDTO = answerDTO;
    }

    public Collection<UserDTO> getListUser() {
        return listUser;
    }

    public void setListUser(Collection<UserDTO> listUser) {
        this.listUser = listUser;
    }

    public AnswerDTO getAnswerDTO() {
        return answerDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }
    
}
