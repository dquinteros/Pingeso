/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.takeAttendance;

import DTOs.NewUserDTO;
import entity.User;
import java.util.Date;
import javax.annotation.PostConstruct;
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
    private NewUserDTO newStudent;
    /**
     * Creates a new instance of addStudentMB
     */
    public addStudentMB() {
    }
    
    @PostConstruct
    public void init() {
        newStudent = new NewUserDTO();
    }
    
    public boolean insertNewStudent(){
    //    return studentManagementSB.insertNewStudent(u, incomeYear);
        return false;
    }
    
}
