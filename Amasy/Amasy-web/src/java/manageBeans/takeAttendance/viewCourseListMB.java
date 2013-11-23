/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans.takeAttendance;

import entity.Course;
import javax.inject.Named;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import manageBeans.utilitiesMB;
import sessionBeans.CoursesDataSBLocal;

/**
 *
 * @author Pingeso
 */
@ManagedBean
@Named(value = "viewListOfCourseMB")
@RequestScoped
public class viewCourseListMB extends utilitiesMB {

    @EJB 
    private CoursesDataSBLocal CoursesDataSB;
    
    @Inject 
    private takeAttendanceConversationMB takeAttendanceConversation;
        
    private ArrayList<Course> listCourse;
    
    public viewCourseListMB() {
    }
    
    @PostConstruct
    public void init() {
        this.listCourse = CoursesDataSB.teacherCourses(0);          
    }

    public void courseRedirect(long idCourse){   
        this.takeAttendanceConversation.beginConversation();
        this.takeAttendanceConversation.setIdClass(idCourse);        
        //para redireccionar
        this.redirection("/faces/teacher/takeAttendance/takeAttendance.xhtml?cid=".concat(this.takeAttendanceConversation.getConversation().getId().toString()));               
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
