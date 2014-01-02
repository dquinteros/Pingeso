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
import managedBeans.LoginSessionMB;
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
    @Inject
    private LoginSessionMB session;
    private ArrayList<Course> listCourse;
    
    /**
     *
     */
    public ViewCourseListMB() {
    }
    
    /**
     *
     */
    @PostConstruct
    public void init() {
        this.listCourse = CoursesDataSB.teacherCourses(session.getUser().getId());          
    }

    /**
     *
     * @param idCourse
     */
    public void courseRedirect(long idCourse){   
        this.takeAttendanceConversation.beginConversation();
        this.takeAttendanceConversation.setIdClass(idCourse);
        System.out.println("idcourse = "+idCourse);
        UtilitiesMB.redirection("/faces/teacher/takeAttendance/takeAttendance.xhtml?cid=".concat(this.takeAttendanceConversation.getConversation().getId().toString()));
    }
    
    /**
     *
     * @return
     */
    public CoursesDataSBLocal getCoursesDataSB() {
        return CoursesDataSB;
    }

    /**
     *
     * @param CoursesDataSB
     */
    public void setCoursesDataSB(CoursesDataSBLocal CoursesDataSB) {
        this.CoursesDataSB = CoursesDataSB;
    }

    /**
     *
     * @return
     */
    public ArrayList<Course> getListCourse() {
        return listCourse;
    }

    /**
     *
     * @param listCourse
     */
    public void setListCourse(ArrayList<Course> listCourse) {
        this.listCourse = listCourse;
    }
}
