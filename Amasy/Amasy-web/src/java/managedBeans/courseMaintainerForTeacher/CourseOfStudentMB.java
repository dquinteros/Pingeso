/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainerForTeacher;

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
import sessionBeans.courseManagement.CourseManagementSBLocal;
import sessionBeans.studentManagement.StudentManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "courseOfStudentMB")
@RequestScoped
public class CourseOfStudentMB {

    @Inject
    private CourseMaintainerOfTeacherConversationalMB courseMaintainerOfTeacherConversation;
    private Long idUser;
    @EJB
    private StudentManagementSBLocal studentManagementSB;
    @EJB
    private CourseManagementSBLocal courseManagementSB;
    private List<CourseDTO> courseList;
    private NewUserDTO newUserDTO;
    private CourseDTO course;
    private List<CourseDTO> filteredCourses;

    /**
     * Creates a new instance of CourseOfStudentMB
     */
    public CourseOfStudentMB() {
    }

    @PostConstruct
    void Init() {
        idUser = courseMaintainerOfTeacherConversation.getIdUser();
        ListCourseDTO listCourseDTO = studentManagementSB.getCourseOfStudent(idUser);
        courseList = new ArrayList(listCourseDTO.getListCourse());
        newUserDTO = studentManagementSB.getStudentById(idUser);
        course = courseManagementSB.getCourseById(courseMaintainerOfTeacherConversation.getIdCourse());
    }

    public List<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseDTO> courseList) {
        this.courseList = courseList;
    }

    public List<CourseDTO> getFilteredCourses() {
        return filteredCourses;
    }

    public void setFilteredCourses(List<CourseDTO> filteredCourses) {
        this.filteredCourses = filteredCourses;
    }

    public NewUserDTO getNewUserDTO() {
        return newUserDTO;
    }

    public void setNewUserDTO(NewUserDTO newUserDTO) {
        this.newUserDTO = newUserDTO;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
}
