/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entity.User;
import javax.ejb.Stateless;

/**
 *
 * @author Pingeso
 */
@Stateless
public class TakeAttendanceDataUserDTO {

    private User user;
    private boolean present;

    public void TakeAttendanceDataUser(User user, boolean present) {
        this.user = user;
        this.present = present;  
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    } 

    

}
