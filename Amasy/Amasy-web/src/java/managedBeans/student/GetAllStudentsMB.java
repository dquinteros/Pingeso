/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.student;

import DTOs.StudentDTO;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.StudentManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "getAllStudentsMB")
@RequestScoped
public class GetAllStudentsMB {
    @EJB
    private StudentManagementSBLocal studentManagementSB;
    private LinkedList<StudentDTO> listStudent = new LinkedList<>();
    /**
     * Creates a new instance of GetAllStudentsMB
     */
    public GetAllStudentsMB() {
    }    
    
    @PostConstruct
    public void init() {      
        listStudent = studentManagementSB.getAllStudent();        
    }
    
    public LinkedList<StudentDTO> getListStudent() {
        return listStudent;
    }

    public void setListStudent(LinkedList<StudentDTO> listStudent) {
        this.listStudent = listStudent;
    }
}
