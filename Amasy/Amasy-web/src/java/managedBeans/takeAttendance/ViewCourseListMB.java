/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.takeAttendance;

import entity.Course;
import javax.inject.Named;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import sessionBeans.CoursesDataSBLocal;

/**
 *
 * @author Pingeso
 */
@ManagedBean
@Named(value = "viewListOfCourseMB")
@RequestScoped
public class ViewCourseListMB{

    @EJB 
    private CoursesDataSBLocal CoursesDataSB;
    
    @Inject 
    private TakeAttendanceConversationMB takeAttendanceConversation;
        
    private ArrayList<Course> listCourse;
    
    public ViewCourseListMB() {
    }
    
    @PostConstruct
    public void init() {
        this.listCourse = CoursesDataSB.teacherCourses(0);          
    }

    public void courseRedirect(long idCourse){   
        this.takeAttendanceConversation.beginConversation();
        this.takeAttendanceConversation.setIdClass(idCourse);   
        UtilitiesMB.redirection("/faces/teacher/takeAttendance/takeAttendance.xhtml?cid=".concat(this.takeAttendanceConversation.getConversation().getId().toString()));
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
