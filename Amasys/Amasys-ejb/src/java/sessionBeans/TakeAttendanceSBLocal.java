/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Student;
import entity.User;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface TakeAttendanceSBLocal {

    public ArrayList<User> listOfStudentsPerCourseList(long course);
    
}
