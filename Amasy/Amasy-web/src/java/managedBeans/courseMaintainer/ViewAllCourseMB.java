/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import DTOs.CourseDTO;
import DTOs.ListCourseDTO;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import managedBeans.courseMaintainerForTeacher.CourseMaintainerOfTeacherConversationalMB;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewAllCourseMB")
@RequestScoped
public class ViewAllCourseMB {

    @EJB
    private CourseManagementSBLocal courseManagementSB;
    @Inject
    private CourseMaintainerConversationalMB courseMaintainerConversation;
    private CourseDTO selectedCourse;
    private List<CourseDTO> filteredCourses;
    @Inject
    private CourseMaintainerOfTeacherConversationalMB courseMaintainerOfTeacherConversation;
    private ListCourseDTO courseListDTO;
    private LinkedList<CourseDTO> courseList; //Depreciated

    /**
     * Creates a new instance of ViewAllCourseMB
     */
    public ViewAllCourseMB() {
    }

    @PostConstruct
    void init() {
        getCourse();
    }

    /**
     *
     * @param idCourse
     */
    public void editCourse(Long idCourse) {
        this.courseMaintainerConversation.beginConversation();
        this.courseMaintainerConversation.setIdCourse(idCourse);
        UtilitiesMB.redirection("/faces/admin/courseMaintainer/editCourse.xhtml?cid=".concat(this.courseMaintainerConversation.getConversation().getId().toString()));
    }

    public void addBlockCourse(Long idCourse) {
        this.courseMaintainerConversation.beginConversation();
        this.courseMaintainerConversation.setIdCourse(idCourse);
        UtilitiesMB.redirection("/faces/admin/courseMaintainer/allocateBlockclassesoToCourse.xhtml?cid=".concat(this.courseMaintainerConversation.getConversation().getId().toString()));
    }

    public void studentsOfCourse(Long idCourse) {
        this.courseMaintainerOfTeacherConversation.beginConversation();
        this.courseMaintainerOfTeacherConversation.setIdCourse(idCourse);
        UtilitiesMB.redirection("/faces/teacher/courses/viewAllStudentsOfCourse.xhtml?cid=".concat(this.courseMaintainerOfTeacherConversation.getConversation().getId().toString()));
    }

    public void assistanceOfCourse(Long idCourse) {
        this.courseMaintainerOfTeacherConversation.beginConversation();
        this.courseMaintainerOfTeacherConversation.setIdCourse(idCourse);
        UtilitiesMB.redirection("/faces/teacher/courses/viewAssistanceOfCourse.xhtml?cid=".concat(this.courseMaintainerOfTeacherConversation.getConversation().getId().toString()));
    }

    /**
     *
     */
    public void getCourse() {
        courseListDTO = courseManagementSB.getAllCourse();
        courseList = new LinkedList<>(courseListDTO.getListCourse());
    }

    /**
     *
     * @return
     */
    public List<CourseDTO> getFilteredCourses() {
        return filteredCourses;
    }

    /**
     *
     * @param filteredCourses
     */
    public void setFilteredCourses(List<CourseDTO> filteredCourses) {
        this.filteredCourses = filteredCourses;
    }

    /**
     *
     * @return
     */
    public CourseDTO getSelectedCourse() {
        return selectedCourse;
    }

    /**
     *
     * @param selectedCourse
     */
    public void setSelectedCourse(CourseDTO selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public LinkedList<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<CourseDTO> courseList) {
        this.courseList = courseList;
    }
}
