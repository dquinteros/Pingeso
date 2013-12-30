/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.LinkedList;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class AssistanceListDTO {

    private LinkedList<AssistanceDTO> listAssistanceDTO;
    private AnswerDTO answer;
    private int UserID;
    private String firstName;
    private String lastName;

    public AssistanceListDTO() {
    }

    public AssistanceListDTO(LinkedList<AssistanceDTO> listAssistanceDTO, AnswerDTO answer) {
        this.listAssistanceDTO = listAssistanceDTO;
        this.answer = answer;
    }

    public LinkedList<AssistanceDTO> getListAssistanceDTO() {
        return listAssistanceDTO;
    }

    public void setListAssistanceDTO(LinkedList<AssistanceDTO> listAssistanceDTO) {
        this.listAssistanceDTO = listAssistanceDTO;
    }

    public AnswerDTO getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerDTO answer) {
        this.answer = answer;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
