/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import DTOs.CourseDTO;
import DTOs.ListCourseDTO;
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
    
    private ListCourseDTO courseListDTO;
    private LinkedList<CourseDTO> courseList; //Depreciated

    /**
     * Creates a new instance of ViewAllCourseMB
     */
    public ViewAllCourseMB() {
    }
    
    @PostConstruct
    void init(){
        getCourse();
    }
    
    /**
     *
     */
    public void getCourse(){        
        courseListDTO = courseManagementSB.getAllCourse();
        courseList = new LinkedList<>(courseListDTO.getListCourse());
    }

    /**
     *
     * @return
     */
    public List<CourseDTO> getFilteredCourses() {
        return filteredCourses;
    }

    /**
     *
     * @param filteredCourses
     */
    public void setFilteredCourses(List<CourseDTO> filteredCourses) {
        this.filteredCourses = filteredCourses;
    }

    /**
     *
     * @return
     */
    public CourseDTO getSelectedCourse() {
        return selectedCourse;
    }

    /**
     *
     * @param selectedCourse
     */
    public void setSelectedCourse(CourseDTO selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public LinkedList<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<CourseDTO> courseList) {
        this.courseList = courseList;
    }
    
    
}
