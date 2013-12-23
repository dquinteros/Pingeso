/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import DTOs.CourseDTO;
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
    private CourseDTO selectedCourse;
    private List<CourseDTO> filteredCourses;
    
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

    public List<CourseDTO> getFilteredCourses() {
        return filteredCourses;
    }

    public void setFilteredCourses(List<CourseDTO> filteredCourses) {
        this.filteredCourses = filteredCourses;
    }

    public CourseDTO getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(CourseDTO selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
    
}
