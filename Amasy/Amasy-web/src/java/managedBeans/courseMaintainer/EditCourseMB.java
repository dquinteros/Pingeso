/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import DTOs.ListUniversityDTO;
import DTOs.UserDTO;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import sessionBeans.courseManagement.CourseManagementSBLocal;
import sessionBeans.teacherManagement.TeacherManagementSBLocal;
import sessionBeans.universityManagement.UniversityManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "editCourseMB")
@RequestScoped
public class EditCourseMB {

    @EJB
    private UniversityManagementSBLocal universityManagementSB;
    @EJB
    private TeacherManagementSBLocal teacherManagementSB;
    @EJB
    private CourseManagementSBLocal courseManagementSB;
    @Inject
    private CourseMaintainerConversationalMB courseMaintainerConversationalMB;
    private CourseDTO currentCourseDTO;
    private Long idCourse;
    private AnswerDTO r;
    private LinkedList<UserDTO> userList;
    private ListUniversityDTO listUniversityDTO;
    private Long selectedIdUniversity;
    private Long selectedIdUser;

    /**
     * Creates a new instance of EditCourseMB
     */
    public EditCourseMB() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        idCourse = courseMaintainerConversationalMB.getIdCourse();
        userList = teacherManagementSB.getAllTeacher();
        listUniversityDTO = universityManagementSB.getAllUniversity();
        currentCourseDTO = courseManagementSB.getCourseById(idCourse);
        selectedIdUniversity = currentCourseDTO.getIdUniversity();
        if(currentCourseDTO.getTeacher()!=null){
            selectedIdUser = currentCourseDTO.getTeacher().getId();
        }                
    }

    public void editCurrentCourse() {
        r = courseManagementSB.updateCourse(currentCourseDTO, idCourse, selectedIdUniversity, selectedIdUser);
        if (r.getIdError() == 0) {
            UtilitiesMB.showFeedback(r);
        } else {
            UtilitiesMB.showFeedback(r);
        }
    }

    public CourseDTO getCurrentCourseDTO() {
        return currentCourseDTO;
    }

    public void setCurrentCourseDTO(CourseDTO currentCourseDTO) {
        this.currentCourseDTO = currentCourseDTO;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public LinkedList<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(LinkedList<UserDTO> userList) {
        this.userList = userList;
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

    public Long getSelectedIdUser() {
        return selectedIdUser;
    }

    public void setSelectedIdUser(Long selectedIdUser) {
        this.selectedIdUser = selectedIdUser;
    }
}
