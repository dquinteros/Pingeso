package managedBeans.courseMaintainer;

import DTOs.CourseDTO;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.LoginSessionMB;
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
    private LinkedList<CourseDTO> courseList;
    private CourseDTO selectedCourse;
    private List<CourseDTO> filteredCourses;
    
    @Inject
    private LoginSessionMB session;
    
    public ViewAllCoursesMB() {
    }
    
    @PostConstruct
    void init(){
        getCourses();
    }

    private void getCourses() {
        courseList = courseManagementSB.getAllCoursesOfTeacher(session.getUser().getId());
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
