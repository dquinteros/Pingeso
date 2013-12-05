/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.takeAttendance;

import DTOs.NewUserDTO;
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
public class AddStudentMB {
    @EJB
    private StudentManagementSBLocal studentManagementSB;
    private NewUserDTO newStudent;    
    /**
     * Creates a new instance of addStudentMB
     */
    public AddStudentMB() {
    }
    
    @PostConstruct
    public void init() {
        newStudent = new NewUserDTO();
    }
    
    public boolean insertNewStudent(){
        
        studentManagementSB.insertNewStudent(newStudent, null);
    //    return studentManagementSB.insertNewStudent(u, enrollYear);
        return false;
    }

    public NewUserDTO getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(NewUserDTO newStudent) {
        this.newStudent = newStudent;
    }
    
    
    
}
