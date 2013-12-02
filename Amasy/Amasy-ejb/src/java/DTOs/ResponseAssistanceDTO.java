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
    private boolean operationValidates;

    public ResponseAssistanceDTO() {
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public ResponseAssistanceDTO(UserDTO userDTO, boolean operationValidates){
        this.userDTO = userDTO;
        this.operationValidates = operationValidates;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public boolean isOperationValidates() {
        return operationValidates;
    }

    public void setResponse(boolean operationValidates) {
        this.operationValidates = operationValidates;
    }
    
    
}
