/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.adminMaintainer;

import DTOs.AnswerDTO;
import DTOs.NewUserDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import managedBeans.teacherMaintainer.TeacherMaintainerConversationalMB;
import sessionBeans.adminManagement.AdminManagementSBLocal;
import sessionBeans.teacherManagement.TeacherManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "editAdminMB")
@RequestScoped
public class EditAdminMB {

    @EJB
    private AdminManagementSBLocal adminManagementSB;
    @Inject
    private AdminMaintainerConversationalMB adminMaintainerConversation;
    private NewUserDTO newUserDTO;    
    private AnswerDTO r;
    private Long adminId;
    
    /**
     * Creates a new instance of EditAdminMB
     */
    public EditAdminMB() {
    }
    
    /**
     *
     */
    @PostConstruct
    public void init() {
        System.out.println(adminMaintainerConversation.getIdUser());
        adminId = adminMaintainerConversation.getIdUser();
        newUserDTO = adminManagementSB.getAdminById(adminId);
    }
    
    public void editCurrentAdmin(){
        r = adminManagementSB.updateAdmin(newUserDTO, adminId);
        if(r.getIdError()==0){
            UtilitiesMB.showFeedback(r);        
        }else{
            UtilitiesMB.showFeedback(r);        
        }        
    }
    
    /**
     *
     * @return
     */
    public NewUserDTO getNewUserDTO() {
        return newUserDTO;
    }
    
    /**
     *
     * @param newUserDTO
     */
    public void setNewUserDTO(NewUserDTO newUserDTO) {
        this.newUserDTO = newUserDTO;
    }
}
