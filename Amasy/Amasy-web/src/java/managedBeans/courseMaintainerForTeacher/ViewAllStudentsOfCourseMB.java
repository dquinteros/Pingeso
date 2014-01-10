package managedBeans.courseMaintainerForTeacher;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import DTOs.ListCourseDTO;
import DTOs.ListUserDTO;
import DTOs.UserDTO;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewAllStudentsOfCourseMB")
@RequestScoped
public class ViewAllStudentsOfCourseMB {

    @EJB
    private CourseManagementSBLocal courseManagementSB;
    @Inject
    private CourseMaintainerOfTeacherConversationalMB courseMaintainerOfTeacherConversation;
    private ListUserDTO userListDTO;
    private UserDTO selectedUser;
    private List<UserDTO> users;
    private List<UserDTO> filteredUsers;
    private Long idCourse;
    private CourseDTO course;

    public ViewAllStudentsOfCourseMB() {
    }

    @PostConstruct
    void init() {
        idCourse = courseMaintainerOfTeacherConversation.getIdCourse();
        course = courseManagementSB.getCourseById(courseMaintainerOfTeacherConversation.getIdCourse());
        getStudents();
    }

    public void getStudents() {
        ListUserDTO ans = courseManagementSB.getAllStudentsFromCourse(idCourse);
        users = new LinkedList<>(ans.getListUser());
        UtilitiesMB.showFeedback(ans.getAnswerDTO());
    }

    public void assistanceOfStudent(Long idStudent) {
        this.courseMaintainerOfTeacherConversation.beginConversation();
        this.courseMaintainerOfTeacherConversation.setIdCourse(idCourse);
        this.courseMaintainerOfTeacherConversation.setIdUser(idStudent);
        UtilitiesMB.redirection("/faces/teacher/courses/viewAssistanceOfStudent.xhtml?cid=".concat(this.courseMaintainerOfTeacherConversation.getConversation().getId().toString()));
    }
    
    public void courseOfStudent(Long idStudent) {
        this.courseMaintainerOfTeacherConversation.beginConversation();
        this.courseMaintainerOfTeacherConversation.setIdCourse(idCourse);
        this.courseMaintainerOfTeacherConversation.setIdUser(idStudent);
        UtilitiesMB.redirection("/faces/teacher/courses/courseOfStudent.xhtml?cid=".concat(this.courseMaintainerOfTeacherConversation.getConversation().getId().toString()));
    }

    public ListUserDTO getUserListDTO() {
        return userListDTO;
    }

    public void setUserListDTO(ListUserDTO userListDTO) {
        this.userListDTO = userListDTO;
    }

    public UserDTO getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserDTO selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<UserDTO> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<UserDTO> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
}
