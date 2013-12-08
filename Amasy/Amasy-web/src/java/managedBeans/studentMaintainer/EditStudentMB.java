package managedBeans.studentMaintainer;

import DTOs.AnswerDTO;
import DTOs.NewUserDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionBeans.studentManagement.StudentManagementSBLocal;

@Named(value = "editStudentMB")
@RequestScoped
public class EditStudentMB {
    private NewUserDTO newUserDTO;    
    private AnswerDTO r;
    @EJB
    private StudentManagementSBLocal studentManagementSB;    
    private Long id;
    
    public EditStudentMB(){
    }
    
    @PostConstruct
    public void init() {
        id = 3L;
        newUserDTO=studentManagementSB.getStudentById(id);
    }
    
    public void editCurrentStudent(int studentId){
        r = studentManagementSB.updateStudent(newUserDTO, studentId);
    }

    public NewUserDTO getNewUserDTO() {
        return newUserDTO;
    }

    public void setNewUserDTO(NewUserDTO newUserDTO) {
        this.newUserDTO = newUserDTO;
    }
    
}
