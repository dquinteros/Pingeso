package managedBeans.coursesForStudent;

import DTOs.CourseDTO;
import DTOs.ListCourseDTO;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.LoginSessionMB;
import managedBeans.UtilitiesMB;
import managedBeans.courseMaintainerForTeacher.CourseMaintainerOfTeacherConversationalMB;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewAllCoursesMB")
@RequestScoped
public class ViewAllCoursesMB {
    @EJB
    private CourseManagementSBLocal courseManagementSB;
    
    @Inject
    private CourseMaintainerOfTeacherConversationalMB courseMaintainerOfTeacherConversation;
    @Inject
    private CoursesForStudentConversationalMB coursesForStudentConversation;
    
    @Inject 
    LoginSessionMB varSession;
    
    private List<CourseDTO> filteredCourses;
    private ListCourseDTO courseListDTO;
    private LinkedList<CourseDTO> courseList;
    
    public ViewAllCoursesMB() {
    }
    
    @PostConstruct
    void init() {
        getCourse();
    }
    
    public void getCourse() {
        courseListDTO = courseManagementSB.getAllCoursesOfStudent(varSession.getUser().getId());
        courseList = new LinkedList<>(courseListDTO.getListCourse());
    }
    
    public void assistanceOfStudent(Long idCourse) {
        this.courseMaintainerOfTeacherConversation.beginConversation();
        this.courseMaintainerOfTeacherConversation.setIdCourse(idCourse);
        this.courseMaintainerOfTeacherConversation.setIdUser(varSession.getUser().getId());
        UtilitiesMB.redirection("/faces/teacher/courses/viewAssistanceOfStudent.xhtml?cid=".concat(this.courseMaintainerOfTeacherConversation.getConversation().getId().toString()));
    }
    
    public void viewInformationOfTeacher(Long idTeacher){
        this.coursesForStudentConversation.beginConversation();
        this.coursesForStudentConversation.setIdTeacher(idTeacher);
        UtilitiesMB.redirection("/faces/student/course/viewInformationOfTeacher.xhtml?cid=".concat(this.coursesForStudentConversation.getConversation().getId().toString()));
    }

    public ListCourseDTO getCourseListDTO() {
        return courseListDTO;
    }

    public void setCourseListDTO(ListCourseDTO courseListDTO) {
        this.courseListDTO = courseListDTO;
    }

    public LinkedList<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<CourseDTO> courseList) {
        this.courseList = courseList;
    }

    public List<CourseDTO> getFilteredCourses() {
        return filteredCourses;
    }

    public void setFilteredCourses(List<CourseDTO> filteredCourses) {
        this.filteredCourses = filteredCourses;
    }
    
}
