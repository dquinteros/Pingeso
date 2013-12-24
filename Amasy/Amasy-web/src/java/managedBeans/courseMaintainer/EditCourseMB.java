/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
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
@Named(value = "editCourseMB")
@RequestScoped
public class EditCourseMB {

    @EJB
    private CourseManagementSBLocal courseManagementSB;
    @Inject
    private CourseMaintainerConversationalMB courseMaintainerConversationalMB;
    private CourseDTO currentCourseDTO;
    private Long idCourse;
    private AnswerDTO r;

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
        System.out.println("idcourse "+idCourse);
        currentCourseDTO = courseManagementSB.getCourseById(idCourse);
        System.out.println("idcourse "+idCourse);
    }

    public void editCurrentCourse(){
        r = courseManagementSB.updateCourse(currentCourseDTO, idCourse);
        if(r.getIdError()==0){
            UtilitiesMB.showFeedback(r);        
        }else{
            UtilitiesMB.showFeedback(r);        
        }             
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
