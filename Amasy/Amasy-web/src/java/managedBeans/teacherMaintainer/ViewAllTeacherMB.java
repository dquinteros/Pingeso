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

@Named(value = "viewAllTeacherMB")
@RequestScoped
public class ViewAllTeacherMB {
    @EJB
    private TeacherManagementSBLocal teacherManagementSB;
    
    @Inject 
    private TeacherMaintainerConversationalMB teacherMaintainerConversation;
    
    private LinkedList<UserDTO> userList;
    private String rut;
    private String firstName;
    private String lastName;
    private int page;
    private int teachersPerPage;
    private UserDTO selectedTeacher;
    private List<UserDTO> filteredTeachers;
    
    public ViewAllTeacherMB() {
    }
    
    @PostConstruct
    void init(){
        getTeacher();
    }
    
    public void getTeacher(){        
        rut="";
        firstName="";
        lastName="";
        page=1;
        teachersPerPage=10;
        userList = teacherManagementSB.getAllTeacher();
        //System.out.println(userListDTO.getTotalPageNumber());
    }
    
    public void editTeacher(Long idUser){
        System.out.println(idUser);
        this.teacherMaintainerConversation.beginConversation();
        this.teacherMaintainerConversation.setIdUser(idUser);
        UtilitiesMB.redirection("/faces/admin/teacherMaintainer/editTeacher.xhtml?cid=".concat(this.teacherMaintainerConversation.getConversation().getId().toString()));
    }
    
    public void deleteTeacher(Long idUser){
        System.out.println(idUser);
        AnswerDTO ans = teacherManagementSB.deleteTeacher(idUser);
        UtilitiesMB.showFeedback(ans);
   }

    public LinkedList<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(LinkedList<UserDTO> userList) {
        this.userList = userList;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTeachersPerPage() {
        return teachersPerPage;
    }

    public void setTeachersPerPage(int teachersPerPage) {
        this.teachersPerPage = teachersPerPage;
    }

    public UserDTO getSelectedTeacher() {
        return selectedTeacher;
    }

    public void setSelectedTeacher(UserDTO selectedTeacher) {
        this.selectedTeacher = selectedTeacher;
    }

    public List<UserDTO> getFilteredTeachers() {
        return filteredTeachers;
    }

    public void setFilteredTeachers(List<UserDTO> filteredTeachers) {
        this.filteredTeachers = filteredTeachers;
    }
    
}
