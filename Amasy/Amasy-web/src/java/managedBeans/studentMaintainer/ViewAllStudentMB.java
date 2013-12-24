/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.studentMaintainer;

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
import sessionBeans.studentManagement.StudentManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewAllStudentMB")
@RequestScoped
public class ViewAllStudentMB {
    @EJB
    private StudentManagementSBLocal studentManagementSB;
    
    @Inject 
    private StudentMaintainerConversationalMB studentMaintainerConversation;
    
    private LinkedList<UserDTO> userList;
    private UserDTO selectedStudent;
    private List<UserDTO> filteredStudents;
    
    /**
     * Creates a new instance of ViewAllStudent
     */        
    
    public ViewAllStudentMB() {
    }
    
    @PostConstruct
    void init(){
        getStudent();
    }
    
    /**
     *
     */
    public void getStudent(){        
        userList = studentManagementSB.getAllStudent();
    }

    /**
     *
     * @param idUser
     */
    public void editStudent(Long idUser){
        this.studentMaintainerConversation.beginConversation();
        this.studentMaintainerConversation.setIdUser(idUser);
        UtilitiesMB.redirection("/faces/admin/studentMaintainer/editStudent.xhtml?cid=".concat(this.studentMaintainerConversation.getConversation().getId().toString()));
    }
    
    /**
     *
     * @param idUser
     */
    public void deleteStudent(Long idUser){
        AnswerDTO ans = studentManagementSB.deleteStudent(idUser);
        UtilitiesMB.showFeedback(ans);
   }
    
    public void addCourseToStudent(Long idUser){
        this.studentMaintainerConversation.beginConversation();
        this.studentMaintainerConversation.setIdUser(idUser);
        UtilitiesMB.redirection("/faces/admin/studentMaintainer/enrollStudentOnCourse.xhtml?cid=".concat(this.studentMaintainerConversation.getConversation().getId().toString()));
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
    public UserDTO getSelectedStudent() {
        return selectedStudent;
    }

    /**
     *
     * @param selectedStudent
     */
    public void setSelectedStudent(UserDTO selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    /**
     *
     * @return
     */
    public List<UserDTO> getFilteredStudents() {
        return filteredStudents;
    }

    /**
     *
     * @param filteredStudents
     */
    public void setFilteredStudents(List<UserDTO> filteredStudents) {
        this.filteredStudents = filteredStudents;
    }    
    
}
