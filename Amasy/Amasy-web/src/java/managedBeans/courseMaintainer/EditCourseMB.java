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
    private String courseName;

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
        System.out.println("Inicializando EditCourseMB...");
        System.out.println("Obteniendo nombre...");
        courseName = courseMaintainerConversationalMB.getCourseName();
        System.out.println("Nombre obtenido:"+courseName);
        System.out.println("Obteniendo DTO...");
        currentCourseDTO = courseManagementSB.getCourseByName(courseName);
        System.out.println("Terminado...");
    }

    public CourseDTO getCurrentCourseDTO() {
        return currentCourseDTO;
    }

    public void setCurrentCourseDTO(CourseDTO currentCourseDTO) {
        this.currentCourseDTO = currentCourseDTO;
    }    

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    
    
}
