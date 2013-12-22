/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import DTOs.NewUserDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import managedBeans.UtilitiesMB;
import static managedBeans.studentMaintainer.AddStudentMB.validateRut;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "addCourseMB")
@RequestScoped
public class AddCourseMB {
    @EJB
    private CourseManagementSBLocal courseManagementSB;
  
    private CourseDTO newCourse; 

    /**
     *
     * @return
     */
    public CourseDTO getNewCourse() {
        return newCourse;
    }

    /**
     *
     * @param newCourse
     */
    public void setNewCourse(CourseDTO newCourse) {
        this.newCourse = newCourse;
    }

    /**
     * Creates a new instance of AddCourseMB
     */
    public AddCourseMB() {
    }
    
    /**
     *
     */
    @PostConstruct
    public void init() {
        newCourse = new CourseDTO();
    }
    
    /**
     *
     */
    public void insertNewCourse(){
        AnswerDTO r = new AnswerDTO();
        r = courseManagementSB.insertNewCourse(newCourse);
        UtilitiesMB.showFeedback(r); 
    }
    
}
