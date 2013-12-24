/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import DTOs.CourseDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "editCourseMB")
@RequestScoped
public class EditCourseMB {

    @EJB
    private CourseManagementSBLocal courseManagementSB;
    @Inject
    private CourseMaintainerConversationalMB courseMaintainerConversationalMB;
    private CourseDTO currentCourseDTO;
    private Long idCourse;

    /**
     * Creates a new instance of EditCourseMB
     */
    public EditCourseMB() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        idCourse = courseMaintainerConversationalMB.getIdCourse();
        currentCourseDTO = courseManagementSB.getCourseById(idCourse);
    }

    public void editCurrentCourse(){
        
    }
    
    public CourseDTO getCurrentCourseDTO() {
        return currentCourseDTO;
    }

    public void setCurrentCourseDTO(CourseDTO currentCourseDTO) {
        this.currentCourseDTO = currentCourseDTO;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }
    
    
}
