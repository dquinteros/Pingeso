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
public class BlockClassListDTO {
    LinkedList<BlockClassDTO> listBlockClass;
    AnswerDTO answer;

    public BlockClassListDTO(){
    }
    
    public BlockClassListDTO(LinkedList<BlockClassDTO> listBlockClass, AnswerDTO answer){
        this.listBlockClass = listBlockClass;
        this.answer = answer;
    }
    
    public LinkedList<BlockClassDTO> getListBlockClass() {
        return listBlockClass;
    }

    public void setListBlockClass(LinkedList<BlockClassDTO> listBlockClass) {
        this.listBlockClass = listBlockClass;
    }

    public AnswerDTO getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerDTO answer) {
        this.answer = answer;
    }        
}
