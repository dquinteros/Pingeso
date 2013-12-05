/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.takeAttendance;

import DTOs.AnswerDTO;
import DTOs.NewUserDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import managedBeans.UtilitiesMB;
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
    
    public void insertNewStudent(){
        AnswerDTO r = studentManagementSB.insertNewStudent(newStudent, null);
        System.out.println(r.getIdError());
        String responseMessage = UtilitiesMB.showResponseServer(r);
        System.out.println(responseMessage);
        //FacesContext fc = FacesContext.getCurrentInstance();
        //FacesContext.getCurrentInstance().getMessages("asdf")
        //fc.addMessage(, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Hola"," Hubo un error"));
    }   
    
    public NewUserDTO getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(NewUserDTO newStudent) {
        this.newStudent = newStudent;
    }
    
    
    
}
