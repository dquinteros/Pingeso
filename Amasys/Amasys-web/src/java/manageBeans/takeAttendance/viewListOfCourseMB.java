/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans.takeAttendance;

import entity.Course;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import sessionBeans.CoursesDataSBLocal;

/**
 *
 * @author Pingeso
 */
@ManagedBean
@Named(value = "viewListOfCourseMB")
@ConversationScoped
public class viewListOfCourseMB implements Serializable {

    @EJB 
    private CoursesDataSBLocal CoursesDataSB;
    
    @Inject 
    private takeAttendanceConversationMB takeAttendanceConversation;
    
    private ArrayList<Course> listCourse;
    
    public viewListOfCourseMB() {
    }
    
    @PostConstruct
    public void init() {
        this.listCourse = CoursesDataSB.teacherCourses(0);          
    }

    public void courseRedirect(long idCourse){   
        this.takeAttendanceConversation.beginConversation();
        this.takeAttendanceConversation.setIdClass(idCourse);        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
           System.out.println(takeAttendanceConversation.getIdClass());
           externalContext.redirect(externalContext.getRequestContextPath() + "/faces/teacher/takeAttendance.xhtml");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }        
    }
    
    public CoursesDataSBLocal getCoursesDataSB() {
        return CoursesDataSB;
    }

    public void setCoursesDataSB(CoursesDataSBLocal CoursesDataSB) {
        this.CoursesDataSB = CoursesDataSB;
    }

    public ArrayList<Course> getListCourse() {
        return listCourse;
    }

    public void setListCourse(ArrayList<Course> listCourse) {
        this.listCourse = listCourse;
    }
}
