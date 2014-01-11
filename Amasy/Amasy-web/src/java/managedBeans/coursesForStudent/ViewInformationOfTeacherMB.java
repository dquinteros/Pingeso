package managedBeans.coursesForStudent;

import DTOs.NewUserDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.teacherManagement.TeacherManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewInformationOfTeacherMB")
@RequestScoped
public class ViewInformationOfTeacherMB {
    @EJB
    private TeacherManagementSBLocal teacherManagementSB;
    
    @Inject
    private CoursesForStudentConversationalMB coursesForStudentConversationalMB;
    
    private NewUserDTO teacher;
    
    public ViewInformationOfTeacherMB() {
    }
    
    @PostConstruct
    void init() {
        teacher = teacherManagementSB.getTeacherById(coursesForStudentConversationalMB.getIdTeacher());
    }

    public NewUserDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(NewUserDTO teacher) {
        this.teacher = teacher;
    }
    
}
