/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.takeAttendance;

import entity.User;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.studentManagement.StudentManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "addStudentMB")
@RequestScoped
public class addStudentMB {
    @EJB
    private StudentManagementSBLocal studentManagementSB;

    /**
     * Creates a new instance of addStudentMB
     */
    public addStudentMB() {
    }
    
    public boolean insertNewStudent(User u, Date incomeYear){
        return studentManagementSB.insertNewStudent(u, incomeYear);
    }
    
}
