/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainerForTeacher;

import DTOs.AssistanceListCourseDTO;
import DTOs.AssistanceListCourseRowDTO;
import DTOs.AssistanceListCourseTitleDTO;
import DTOs.AssistanceListCourseUnitDTO;
import DTOs.ResponseAssistanceDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.LoginSessionMB;
import managedBeans.UtilitiesMB;
import sessionBeans.TakeAttendanceSB;
import sessionBeans.TakeAttendanceSBLocal;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewAssistanceOfCourseMB")
@RequestScoped
public class ViewAssistanceOfCourseMB {

    /**
     * Creates a new instance of ViewAssistanceOfCourseMB
     */
    @EJB
    private CourseManagementSBLocal courseManagementSB;
    @EJB
    private TakeAttendanceSBLocal takeAttendanceSB;
    
    @Inject
    private CourseMaintainerOfTeacherConversationalMB courseMaintainerOfTeacherConversation;
    private Long idCourse;
    private AssistanceListCourseDTO assistanceListCourse;
    private String assistanceState;
    private Map<String, String> assistanceStates = new HashMap<String, String>();

    public ViewAssistanceOfCourseMB() {
        assistanceStates.put("Ausente", "Ausente");
        assistanceStates.put("Justificado", "Justificado");

    }

    @PostConstruct
    void Init() {
        idCourse = courseMaintainerOfTeacherConversation.getIdCourse();
        assistanceListCourse = courseManagementSB.assistanceListCourse(idCourse);

    }

    public AssistanceListCourseDTO getAssistanceListCourse() {
        return assistanceListCourse;
    }

    public void setAssistanceListCourse(AssistanceListCourseDTO assistanceListCourse) {
        this.assistanceListCourse = assistanceListCourse;
    }

    public boolean isAssistance(String assistance) {
        switch (assistance) {
            case "Ausente":
                return true;
            case "Presente":
                return true;
            case "Justificado":
                return true;
            default:
                return false;
        }

    }

    public void setAssistance(ArrayList row, Long idBlockClass) {
         
        String rut = "";
        for (Object u : row) {
            AssistanceListCourseUnitDTO unit = (AssistanceListCourseUnitDTO) u;
            if (unit.isTextRutName()) {
                if (unit.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
                    rut = unit.getText();
                }
            }
        }
        System.out.println(assistanceState);
        if(assistanceState != null && !assistanceState.equals("")){
        ResponseAssistanceDTO responseAssistance = takeAttendanceSB.setAssistance(rut, idBlockClass, assistanceState);
        String message = "";
        switch (responseAssistance.getAnswer().getIdError()) {
            case 115:
                message = responseAssistance.getUserDTO().getFirstName() + responseAssistance.getUserDTO().getLastName();
                break;
            case 116:
                message = responseAssistance.getUserDTO().getFirstName() + responseAssistance.getUserDTO().getLastName();
                break;
        }
        UtilitiesMB.showFeedback(responseAssistance.getAnswer(), message);    }                
    }

    public String getAssistanceState() {
        return assistanceState;
    }

    public void setAssistanceState(String assistanceState) {
        this.assistanceState = assistanceState;
    }

    public Map<String, String> getAssistanceStates() {
        return assistanceStates;
    }

    public void setAssistanceStates(Map<String, String> assistanceStates) {
        this.assistanceStates = assistanceStates;
    }

    public TakeAttendanceSBLocal getTakeAttendanceSB() {
        return takeAttendanceSB;
    }

    public void setTakeAttendanceSB(TakeAttendanceSBLocal takeAttendanceSB) {
        this.takeAttendanceSB = takeAttendanceSB;
    }
    
    
}
