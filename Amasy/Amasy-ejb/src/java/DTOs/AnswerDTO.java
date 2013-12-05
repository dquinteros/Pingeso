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
public class AnswerDTO {
    private int idError;

    public AnswerDTO() {
    }
    
    public AnswerDTO(int idError){
        this.idError = idError;
    }
    
    public boolean isValid(){
        if(idError==0){
            return true;
        }else{
            return false;
        }
    }
    
    public int getIdError() {
        return idError;
    }

    public void setIdError(int idError) {
        this.idError = idError;
    }               
    
}
