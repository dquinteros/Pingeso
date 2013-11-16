/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans;

import entity.User;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import manageBeans.takeAttendance.takeAttendanceConversationMB;
import sessionBeans.TakeAttendanceSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "takeAttendanceMB")
@RequestScoped
public class takeAttendanceMB {

    @EJB
    private TakeAttendanceSBLocal TakeAttendanceSB;
    private ArrayList<User> listStudents;
    
    @Inject 
    private takeAttendanceConversationMB takeAttendanceConversation;
    
    public takeAttendanceMB() {
        
    }
    
    @PostConstruct
    public void init() {                
        System.out.println(this.takeAttendanceConversation.getIdClass());
        if(this.takeAttendanceConversation.getIdClass()!=-1){
            this.listStudents = TakeAttendanceSB.listOfStudentsPerCourseList(this.takeAttendanceConversation.getIdClass());            
        }else{
            
        }        
    }

    public TakeAttendanceSBLocal getTakeAttendanceSB() {
        return TakeAttendanceSB;
    }

    public void setTakeAttendanceSB(TakeAttendanceSBLocal TakeAttendanceSB) {
        this.TakeAttendanceSB = TakeAttendanceSB;
    }

    public ArrayList<User> getListStudents() {
        return listStudents;
    }

    public void setListStudents(ArrayList<User> listStudents) {
        this.listStudents = listStudents;
    }
    
    
    
    
}
