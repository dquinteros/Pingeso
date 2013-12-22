/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entity.User;
import java.util.Date;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class CourseAssistanceInformationDTO {
    private User user;
    //private ArrayList<boolean> listAssistance;    
    
    /**
     *
     */
    @Schedule(minute = "*", second = "0", dayOfMonth = "*", month = "*", year = "*", hour = "9-17", dayOfWeek = "Mon-Fri")
    public void myTimer() {
        System.out.println("Timer event: " + new Date());
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
