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
    private String idError;

    public AnswerDTO() {
    }
    
    public AnswerDTO(String idError){
        this.idError = idError;
    }
    
    public boolean isValid(){
        if("000".equals(idError)){
            return true;
        }else{
            return false;
        }
    }
    
    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }               
    
}
