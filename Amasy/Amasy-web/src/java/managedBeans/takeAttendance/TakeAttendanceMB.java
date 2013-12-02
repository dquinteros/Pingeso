/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.takeAttendance;

import DTOs.ResponseAssistanceDTO;
import entity.BlockClass;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import DTOs.UserAssistantBlockClassDTO;
import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sessionBeans.TakeAttendanceSBLocal;


/**
 *
 * @author Pingeso
 */
@Named(value = "takeAttendanceMB")
@RequestScoped
public class TakeAttendanceMB{

    @EJB
    private TakeAttendanceSBLocal TakeAttendanceSB;
    private ArrayList<UserAssistantBlockClassDTO> listStudents;
    private BlockClass blockClass;
    private String fingerprint;
    
    @Inject 
    private TakeAttendanceConversationMB takeAttendanceConversation;
    
    public TakeAttendanceMB() {
        
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
                UtilitiesMB.redirection("/faces/teacher/takeAttendance/viewCourseList.xhtml");
            }            
        }else{
            UtilitiesMB.redirection("/faces/teacher/takeAttendance/viewCourseList.xhtml");
        }        
    }
    
    public void sendFingerprint(ActionEvent actionEvent) {        
        ResponseAssistanceDTO responseAssistance = TakeAttendanceSB.validateFingerprintBM(fingerprint, blockClass);
        FacesContext context = FacesContext.getCurrentInstance();  
        if(responseAssistance==null){            
            context.addMessage(null, new FacesMessage("error al reconocer la huella", "En caso de pertenecer al curso y su huella este registrada repita la operacion"));  
        }else{
            if(responseAssistance.isOperationValidates()){
                updateAssistance(responseAssistance);
                context.addMessage(null, new FacesMessage("Alumno marcado como presente", "el alumno :"+ responseAssistance.getUserDTO().getFirstName() +" " +responseAssistance.getUserDTO().getLastName() +" fue marcado como presente"));  
            }else{
                context.addMessage(null, new FacesMessage("Alumno ya se encontraba presente", "el alumno :"+ responseAssistance.getUserDTO().getFirstName() +" ya se encuentra presente"));  
            }            
        }
    }

    private void updateAssistance(ResponseAssistanceDTO responseAssistance){
        for(int i=0; i<listStudents.size(); i++){
            if(listStudents.get(i).getUser().getId()==responseAssistance.getUserDTO().getId()){
                listStudents.get(i).setPresent(true);
                break;
            }
        }
    }
    
    public TakeAttendanceSBLocal getTakeAttendanceSB() {
        return TakeAttendanceSB;
    }

    public void setTakeAttendanceSB(TakeAttendanceSBLocal TakeAttendanceSB) {
        this.TakeAttendanceSB = TakeAttendanceSB;
    }

    public ArrayList<UserAssistantBlockClassDTO> getListStudents() {
        return listStudents;
    }

    public void setListStudents(ArrayList<UserAssistantBlockClassDTO> listStudents) {
        this.listStudents = listStudents;
    }

    public BlockClass getBlockClass() {
        return blockClass;
    }

    public void setBlockClass(BlockClass blockClass) {
        this.blockClass = blockClass;
    }

    public TakeAttendanceConversationMB getTakeAttendanceConversation() {
        return takeAttendanceConversation;
    }

    public void setTakeAttendanceConversation(TakeAttendanceConversationMB takeAttendanceConversation) {
        this.takeAttendanceConversation = takeAttendanceConversation;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }


    
    
    
    
}
