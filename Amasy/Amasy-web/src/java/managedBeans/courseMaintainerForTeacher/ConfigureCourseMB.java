/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainerForTeacher;

import DTOs.CourseDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "configureCourseMB")
@RequestScoped
public class ConfigureCourseMB {
    @EJB
    private CourseManagementSBLocal courseManagementSB;

    private CourseDTO course; 
    
    public ConfigureCourseMB() {
    }
    
    @PostConstruct
    public void init() {
        course = new CourseDTO();
    }
    
}
