/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.adminMaintainer;

import DTOs.AnswerDTO;
import DTOs.UserDTO;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import managedBeans.teacherMaintainer.TeacherMaintainerConversationalMB;
import sessionBeans.adminManagement.AdminManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewAllAdminMB")
@RequestScoped
public class ViewAllAdminMB {
    @EJB
    private AdminManagementSBLocal adminManagementSB;
    
    @Inject 
    private AdminMaintainerConversationalMB adminMaintainerConversation;
    
    private LinkedList<UserDTO> userList;
    private UserDTO selectedAdmin;
    private List<UserDTO> filteredAdmins;

    /**
     * Creates a new instance of ViewAllAdminMB
     */
    public ViewAllAdminMB() {
    }
    
    @PostConstruct
    void init(){
        getAdmin();
    }
    
    public void editAdmin(Long idUser){
        System.out.println(idUser);
        this.adminMaintainerConversation.beginConversation();
        this.adminMaintainerConversation.setIdUser(idUser);
        UtilitiesMB.redirection("/faces/admin/adminMaintainer/editAdmin.xhtml?cid=".concat(this.adminMaintainerConversation.getConversation().getId().toString()));
    }
    
    public void deleteAdmin(Long idUser){        
        AnswerDTO ans = adminManagementSB.deleteAdmin(idUser);
        if(ans.getIdError()==0){
            for(UserDTO it: userList){
                if(it.getId()==idUser){
                    userList.remove(it);
                    break;
                }
            }
        }
        UtilitiesMB.showFeedback(ans);
   }
    
    public void getAdmin(){       
        userList = adminManagementSB.getAllAdmin();
    }

    public LinkedList<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(LinkedList<UserDTO> userList) {
        this.userList = userList;
    }

    public UserDTO getSelectedAdmin() {
        return selectedAdmin;
    }

    public void setSelectedAdmin(UserDTO selectedAdmin) {
        this.selectedAdmin = selectedAdmin;
    }

    public List<UserDTO> getFilteredAdmins() {
        return filteredAdmins;
    }

    public void setFilteredAdmins(List<UserDTO> filteredAdmins) {
        this.filteredAdmins = filteredAdmins;
    }
    
}
