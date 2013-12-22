/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class ResponseAssistanceDTO {
    private UserDTO userDTO;
    private AnswerDTO answer;

    /**
     *
     */
    public ResponseAssistanceDTO() {
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /**
     *
     * @param userDTO
     */
    public ResponseAssistanceDTO(UserDTO userDTO){
        this.userDTO = userDTO;
    }

    /**
     *
     * @return
     */
    public UserDTO getUserDTO() {
        return userDTO;
    }

    /**
     *
     * @param userDTO
     */
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    /**
     *
     * @return
     */
    public AnswerDTO getAnswer() {
        return answer;
    }

    /**
     *
     * @param answer
     */
    public void setAnswer(AnswerDTO answer) {
        this.answer = answer;
    }
    
    
}
