/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Course;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface CoursesDataSBLocal {
    
    /**
     *
     * @param profesorActual
     * @return
     */
    public ArrayList<Course> teacherCourses(long profesorActual);
    
}
