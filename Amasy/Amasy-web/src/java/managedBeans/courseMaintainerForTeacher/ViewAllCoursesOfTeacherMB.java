package managedBeans.courseMaintainerForTeacher;

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
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewAllCoursesOfTeacherMB")
@RequestScoped
public class ViewAllCoursesOfTeacherMB {
    @EJB
    private CourseManagementSBLocal courseManagementSB;
    private ListCourseDTO courseListDTO;
    private LinkedList<CourseDTO> courseList;
    private CourseDTO selectedCourse;
    private List<CourseDTO> filteredCourses;
    
    @Inject
    private LoginSessionMB session;
    
    @Inject 
    private CourseMaintainerOfTeacherConversationalMB courseMaintainerOfTeacherConversation;
    
    public ViewAllCoursesOfTeacherMB() {
    }
    
    @PostConstruct
    void init(){
        getCourses();
    }

    private void getCourses() {
        courseListDTO = courseManagementSB.getAllCoursesOfTeacher(session.getUser().getId());
        courseList = new LinkedList<>(courseListDTO.getListCourse());
    }
   
    public void configureCourse(Long idCourse){
        this.courseMaintainerOfTeacherConversation.beginConversation();
        this.courseMaintainerOfTeacherConversation.setIdCourse(idCourse);
        UtilitiesMB.redirection("/faces/teacher/courses/configureCourse.xhtml?cid=".concat(this.courseMaintainerOfTeacherConversation.getConversation().getId().toString()));
    }
    
    public LinkedList<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<CourseDTO> courseList) {
        this.courseList = courseList;
    }

    public CourseDTO getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(CourseDTO selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public List<CourseDTO> getFilteredCourses() {
        return filteredCourses;
    }

    public void setFilteredCourses(List<CourseDTO> filteredCourses) {
        this.filteredCourses = filteredCourses;
    }
}
