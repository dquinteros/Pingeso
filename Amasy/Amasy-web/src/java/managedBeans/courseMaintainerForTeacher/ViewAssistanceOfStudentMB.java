package managedBeans.courseMaintainerForTeacher;

import DTOs.AssistanceDTO;
import DTOs.AssistanceListDTO;
import DTOs.NewUserDTO;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.studentMaintainer.StudentMaintainerConversationalMB;
import sessionBeans.studentManagement.StudentManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewAssistanceOfStudentMB")
@RequestScoped
public class ViewAssistanceOfStudentMB {

    @EJB
    private StudentManagementSBLocal studentManagementSB;
    @Inject
    private CourseMaintainerOfTeacherConversationalMB courseMaintainerOfTeacherConversation;
    private Long idUser;
    private Long idCourse;
    private NewUserDTO newUserDTO;
    private AssistanceListDTO assistanceListDTO;
    private LinkedList<AssistanceDTO> assistanceList;
    private LinkedList<AssistanceDTO> filteredAssistance;

    public ViewAssistanceOfStudentMB() {
    }

    @PostConstruct
    private void init() {
        idUser = courseMaintainerOfTeacherConversation.getIdUser();
        idCourse = courseMaintainerOfTeacherConversation.getIdCourse();
        getDataOfStudent();
        assistanceListDTO = studentManagementSB.getAssistanceStudent(idCourse, idUser);
        assistanceList = assistanceListDTO.getListAssistanceDTO();
    }

    private void getDataOfStudent() {
        newUserDTO = studentManagementSB.getStudentById(idUser);
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public NewUserDTO getNewUserDTO() {
        return newUserDTO;
    }

    public void setNewUserDTO(NewUserDTO newUserDTO) {
        this.newUserDTO = newUserDTO;
    }

    public LinkedList<AssistanceDTO> getAssistanceList() {
        return assistanceList;
    }

    public void setAssistanceList(LinkedList<AssistanceDTO> assistanceList) {
        this.assistanceList = assistanceList;
    }

    public LinkedList<AssistanceDTO> getFilteredAssistance() {
        return filteredAssistance;
    }

    public void setFilteredAssistance(LinkedList<AssistanceDTO> filteredAssistance) {
        this.filteredAssistance = filteredAssistance;
    }
}
