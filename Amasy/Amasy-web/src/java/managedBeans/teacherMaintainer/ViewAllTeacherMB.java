package managedBeans.teacherMaintainer;

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
import sessionBeans.teacherManagement.TeacherManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewAllTeacherMB")
@RequestScoped
public class ViewAllTeacherMB {
    @EJB
    private TeacherManagementSBLocal teacherManagementSB;
    
    
    @Inject 
    private TeacherMaintainerConversationalMB teacherMaintainerConversation;
    
    private LinkedList<UserDTO> userList;
    private UserDTO selectedTeacher;
    private List<UserDTO> filteredTeachers;
    
    /**
     *
     */
    public ViewAllTeacherMB() {
    }
    
    @PostConstruct
    void init(){
        getTeacher();
    }
    
    /**
     *
     */
    public void getTeacher(){       
        userList = teacherManagementSB.getAllTeacher();
    }
    
    /**
     *
     * @param idUser
     */
    public void editTeacher(Long idUser){
        this.teacherMaintainerConversation.beginConversation();
        this.teacherMaintainerConversation.setIdUser(idUser);
        UtilitiesMB.redirection("/faces/admin/teacherMaintainer/editTeacher.xhtml?cid=".concat(this.teacherMaintainerConversation.getConversation().getId().toString()));
    }
    
    /**
     *
     * @param idUser
     */
    public void deleteTeacher(Long idUser){        
        AnswerDTO ans = teacherManagementSB.deleteTeacher(idUser);
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
    
    public void courseOfTeacher(Long idUser){
        this.teacherMaintainerConversation.beginConversation();
        this.teacherMaintainerConversation.setIdUser(idUser);
        UtilitiesMB.redirection("/faces/admin/teacherMaintainer/courseOfTeacher.xhtml?cid=".concat(this.teacherMaintainerConversation.getConversation().getId().toString()));
    }

    /**
     *
     * @return
     */
    public LinkedList<UserDTO> getUserList() {
        return userList;
    }

    /**
     *
     * @param userList
     */
    public void setUserList(LinkedList<UserDTO> userList) {
        this.userList = userList;
    }

    /**
     *
     * @return
     */
    public UserDTO getSelectedTeacher() {
        return selectedTeacher;
    }

    /**
     *
     * @param selectedTeacher
     */
    public void setSelectedTeacher(UserDTO selectedTeacher) {
        this.selectedTeacher = selectedTeacher;
    }

    /**
     *
     * @return
     */
    public List<UserDTO> getFilteredTeachers() {
        return filteredTeachers;
    }

    /**
     *
     * @param filteredTeachers
     */
    public void setFilteredTeachers(List<UserDTO> filteredTeachers) {
        this.filteredTeachers = filteredTeachers;
    }
    
}
