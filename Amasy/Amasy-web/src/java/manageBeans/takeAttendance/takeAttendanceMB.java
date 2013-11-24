/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans.takeAttendance;

import entity.BlockClass;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import manageBeans.utilitiesMB;
import DTOs.TakeAttendanceDataUserDTO;
import sessionBeans.TakeAttendanceSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "takeAttendanceMB")
@RequestScoped
public class takeAttendanceMB extends utilitiesMB{

    @EJB
    private TakeAttendanceSBLocal TakeAttendanceSB;
    private ArrayList<TakeAttendanceDataUserDTO> listStudents;
    private BlockClass blockClass;
    
    @Inject 
    private takeAttendanceConversationMB takeAttendanceConversation;
    
    public takeAttendanceMB() {
        
    }
    
    @PostConstruct
    public void init() {      
        System.out.println(takeAttendanceConversation.getIdClass());
        if(takeAttendanceConversation.getIdClass()!=-1){
            blockClass = TakeAttendanceSB.getIdBloackClassForTakeAttendance(this.takeAttendanceConversation.getIdClass());            
            takeAttendanceConversation.setBlockClass(blockClass);
            if(blockClass!=null){   
                System.out.println(blockClass.getDate());
                listStudents = TakeAttendanceSB.listOfStudentsPerCourseList(this.takeAttendanceConversation.getIdClass(), blockClass.getId());            
            }else{
                this.redirection("/faces/teacher/takeAttendance/viewCourseList.xhtml"); 
            }            
        }else{
            this.redirection("/faces/teacher/takeAttendance/viewCourseList.xhtml"); 
        }        
    }

    public TakeAttendanceSBLocal getTakeAttendanceSB() {
        return TakeAttendanceSB;
    }

    public void setTakeAttendanceSB(TakeAttendanceSBLocal TakeAttendanceSB) {
        this.TakeAttendanceSB = TakeAttendanceSB;
    }

    public ArrayList<TakeAttendanceDataUserDTO> getListStudents() {
        return listStudents;
    }

    public void setListStudents(ArrayList<TakeAttendanceDataUserDTO> listStudents) {
        this.listStudents = listStudents;
    }

    public BlockClass getBlockClass() {
        return blockClass;
    }

    public void setBlockClass(BlockClass blockClass) {
        this.blockClass = blockClass;
    }

    public takeAttendanceConversationMB getTakeAttendanceConversation() {
        return takeAttendanceConversation;
    }

    public void setTakeAttendanceConversation(takeAttendanceConversationMB takeAttendanceConversation) {
        this.takeAttendanceConversation = takeAttendanceConversation;
    }


    
    
    
    
}
