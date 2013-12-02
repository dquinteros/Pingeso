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
public class UserAssistantBlockClassDTO {
    private UserDTO userDTO;
    private boolean present;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void TakeAttendanceDataUser(UserDTO user, boolean present) {        
        this.userDTO = user;
        this.present = present;  
    }
    
    public UserDTO getUser() {
        return userDTO;
    }

    public void setUser(UserDTO user) {
        this.userDTO = user;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }   
}
