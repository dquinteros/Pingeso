/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.adminMaintainer;

import DTOs.UserDTO;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
