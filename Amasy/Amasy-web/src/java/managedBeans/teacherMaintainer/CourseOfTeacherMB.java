/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.teacherMaintainer;

import DTOs.CourseDTO;
import DTOs.ListCourseDTO;
import DTOs.NewUserDTO;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
@Named(value = "courseOfTeacherMB")
@RequestScoped
public class CourseOfTeacherMB {

    @EJB
    private TeacherManagementSBLocal teacherManagementSB;
    @Inject
    private TeacherMaintainerConversationalMB teacherMaintainerConversation;
    private Long idUser;
    private ListCourseDTO listCourseDTO;
    private LinkedList<CourseDTO> courseList;
    private NewUserDTO newUserDTO;
    private List<CourseDTO> filteredCourses;

    /**
     * Creates a new instance of CourseOfTeacherMB
     */
    public CourseOfTeacherMB() {
    }

    @PostConstruct
    public void init() {
        idUser = teacherMaintainerConversation.getIdUser();
        listCourseDTO = teacherManagementSB.getCourseOfTeacher(idUser);
        courseList = new LinkedList(listCourseDTO.getListCourse());
        newUserDTO = teacherManagementSB.getTeacherById(idUser);
    }

    public LinkedList<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<CourseDTO> courseList) {
        this.courseList = courseList;
    }

    public NewUserDTO getNewUserDTO() {
        return newUserDTO;
    }

    public void setNewUserDTO(NewUserDTO newUserDTO) {
        this.newUserDTO = newUserDTO;
    }

    public ListCourseDTO getListCourseDTO() {
        return listCourseDTO;
    }

    public void setListCourseDTO(ListCourseDTO listCourseDTO) {
        this.listCourseDTO = listCourseDTO;
    }

    public List<CourseDTO> getFilteredCourses() {
        return filteredCourses;
    }

    public void setFilteredCourses(List<CourseDTO> filteredCourses) {
        this.filteredCourses = filteredCourses;
    }
    
}
