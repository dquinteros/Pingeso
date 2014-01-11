/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.teacherMaintainer;

import DTOs.CourseDTO;
import DTOs.ListCourseDTO;
import DTOs.NewUserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.studentManagement.StudentManagementSBLocal;
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
    private List<CourseDTO> courseList;
    private NewUserDTO newUserDTO;
    private CourseDTO filteredCourses;

    /**
     * Creates a new instance of CourseOfTeacherMB
     */
    public CourseOfTeacherMB() {
    }

    @PostConstruct
    public void init() {
        idUser = teacherMaintainerConversation.getIdUser();
        listCourseDTO = teacherManagementSB.getCourseOfTeacher(idUser);
        courseList = new ArrayList(listCourseDTO.getListCourse());
        newUserDTO = teacherManagementSB.getTeacherById(idUser);
    }

    public List<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseDTO> courseList) {
        this.courseList = courseList;
    }

    public NewUserDTO getNewUserDTO() {
        return newUserDTO;
    }

    public void setNewUserDTO(NewUserDTO newUserDTO) {
        this.newUserDTO = newUserDTO;
    }

    public CourseDTO getFilteredCourses() {
        return filteredCourses;
    }

    public void setFilteredCourses(CourseDTO filteredCourses) {
        this.filteredCourses = filteredCourses;
    }
}
