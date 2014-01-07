package managedBeans.studentMaintainer;

import DTOs.AnswerDTO;
import DTOs.ListUniversityDTO;
import DTOs.NewUserDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import sessionBeans.studentManagement.StudentManagementSBLocal;
import sessionBeans.universityManagement.UniversityManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "editStudentMB")
@RequestScoped
public class EditStudentMB {

    @EJB
    private UniversityManagementSBLocal universityManagementSB;
    @EJB
    private StudentManagementSBLocal studentManagementSB;
    @Inject
    private StudentMaintainerConversationalMB studentMaintainerConversation;
    private NewUserDTO newUserDTO;
    private AnswerDTO r;
    private Long studentId;
    private ListUniversityDTO listUniversityDTO;
    private Long selectedIdUniversity;

    /**
     *
     */
    public EditStudentMB() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        studentId = studentMaintainerConversation.getIdUser();
        newUserDTO = studentManagementSB.getStudentById(studentId);
        listUniversityDTO = universityManagementSB.getAllUniversity();
        selectedIdUniversity = newUserDTO.getUniversityDTO().getId();
    }

    /**
     *
     */
    public void editCurrentStudent() {
        r = studentManagementSB.updateStudent(newUserDTO, studentId);
        if (r.getIdError() == 0) {
            UtilitiesMB.showFeedback(r);
        } else {
            UtilitiesMB.showFeedback(r);
        }
    }

    /**
     *
     * @return
     */
    public NewUserDTO getNewUserDTO() {
        return newUserDTO;
    }

    /**
     *
     * @param newUserDTO
     */
    public void setNewUserDTO(NewUserDTO newUserDTO) {
        this.newUserDTO = newUserDTO;
    }

    public ListUniversityDTO getListUniversityDTO() {
        return listUniversityDTO;
    }

    public void setListUniversityDTO(ListUniversityDTO listUniversityDTO) {
        this.listUniversityDTO = listUniversityDTO;
    }

    public Long getSelectedIdUniversity() {
        return selectedIdUniversity;
    }

    public void setSelectedIdUniversity(Long selectedIdUniversity) {
        this.selectedIdUniversity = selectedIdUniversity;
    }
}
