package managedBeans.studentMaintainer;

import DTOs.AnswerDTO;
import DTOs.NewUserDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import sessionBeans.studentManagement.StudentManagementSBLocal;

@Named(value = "editStudentMB")
@RequestScoped
public class EditStudentMB {    
    @EJB
    private StudentManagementSBLocal studentManagementSB;    
    @Inject
    private StudentMaintainerConversationalMB studentMaintainerConversation;
    private NewUserDTO newUserDTO;    
    private AnswerDTO r;
    private Long studentId;
    
    public EditStudentMB(){
    }
    
    @PostConstruct
    public void init() {
        System.out.println(studentMaintainerConversation.getIdUser());
        studentId = studentMaintainerConversation.getIdUser();
        newUserDTO=studentManagementSB.getStudentById(studentId);
    }
    
    public void editCurrentStudent(){
        r = studentManagementSB.updateStudent(newUserDTO, studentId);
        UtilitiesMB.showFeedback(r);        
    }

    public NewUserDTO getNewUserDTO() {
        return newUserDTO;
    }

    public void setNewUserDTO(NewUserDTO newUserDTO) {
        this.newUserDTO = newUserDTO;
    }
    
}
