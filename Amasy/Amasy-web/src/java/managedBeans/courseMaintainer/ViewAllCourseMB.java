/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import DTOs.CourseDTO;
import DTOs.UserDTO;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewAllCourseMB")
@RequestScoped
public class ViewAllCourseMB {
    @EJB
    private CourseManagementSBLocal courseManagementSB;
    private UserDTO selectedCourse;
    private List<UserDTO> filteredCourses;
    
    private LinkedList<CourseDTO> courseList;

    /**
     * Creates a new instance of ViewAllCourseMB
     */
    public ViewAllCourseMB() {
    }
    
    @PostConstruct
    void init(){
        getCourse();
    }
    
    public void getCourse(){        
        courseList = courseManagementSB.getAllCourse();
    }

    public LinkedList<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<CourseDTO> courseList) {
        this.courseList = courseList;
    }

    public List<UserDTO> getFilteredCourses() {
        return filteredCourses;
    }

    public void setFilteredCourses(List<UserDTO> filteredCourses) {
        this.filteredCourses = filteredCourses;
    }

    public UserDTO getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(UserDTO selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
    
}
