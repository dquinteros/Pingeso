/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.teacherMaintainer;

import DTOs.AnswerDTO;
import DTOs.NewUserDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import sessionBeans.teacherManagement.TeacherManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "editTeacherMB")
@RequestScoped
public class EditTeacherMB {
    @EJB
    private TeacherManagementSBLocal teacherManagementSB;
    @Inject
    private TeacherMaintainerConversationalMB teacherMaintainerConversation;
    private NewUserDTO newUserDTO;    
    private AnswerDTO r;
    private Long teacherId;

    public EditTeacherMB() {
    }
    
    @PostConstruct
    public void init() {
        System.out.println(teacherMaintainerConversation.getIdUser());
        teacherId = teacherMaintainerConversation.getIdUser();
        newUserDTO=teacherManagementSB.getTeacherById(teacherId);
    }
    
    public void editCurrentTeacher(){
        r = teacherManagementSB.updateTeacher(newUserDTO, teacherId);
        if(r.getIdError()==0){
            UtilitiesMB.showFeedback(r);        
        }else{
            UtilitiesMB.showFeedback(r);        
        }        
    }

    public NewUserDTO getNewUserDTO() {
        return newUserDTO;
    }

    public void setNewUserDTO(NewUserDTO newUserDTO) {
        this.newUserDTO = newUserDTO;
    }
    
}
