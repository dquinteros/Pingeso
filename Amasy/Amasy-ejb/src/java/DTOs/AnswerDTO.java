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

    /**
     *
     */
    public AnswerDTO() {
    }
    
    /**
     *
     * @param idError
     */
    public AnswerDTO(int idError){
        this.idError = idError;
    }
    
    /**
     *
     * @return
     */
    public boolean isValid(){
        if(idError==0){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     *
     * @return
     */
    public int getIdError() {
        return idError;
    }

    /**
     *
     * @param idError
     */
    public void setIdError(int idError) {
        this.idError = idError;
    }               
    
}
