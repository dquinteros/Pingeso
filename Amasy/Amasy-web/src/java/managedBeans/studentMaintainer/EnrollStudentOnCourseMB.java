/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.studentMaintainer;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import DTOs.ListCourseDTO;
import DTOs.NewUserDTO;
import entity.Course;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import sessionBeans.courseManagement.CourseManagementSBLocal;
import sessionBeans.studentManagement.StudentManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "enrollStudentOnCourseMB")
@RequestScoped
public class EnrollStudentOnCourseMB {

    @EJB
    private CourseManagementSBLocal courseManagementSB;
    @EJB
    private StudentManagementSBLocal studentManagementSB;
    @Inject
    private StudentMaintainerConversationalMB studentMaintainerConversation;
    private Long idUser;
    private String selectedCourse;
    ///
    private LinkedList<CourseDTO> listCourseFromStudent;
    private CourseDTO selectedCourseFromStudent;
    private List<Course> filteredListCourseFromStudent;
    ///
    private LinkedList<CourseDTO> listCourseFromAll;
    private CourseDTO selectedCourseFromAll;
    private List<Course> filteredListCourseFromAll;
    private NewUserDTO newUserDTO;

    /**
     *
     */
    public EnrollStudentOnCourseMB() {
    }

    @PostConstruct
    private void init() {
        idUser = studentMaintainerConversation.getIdUser();
        getAllCourseFromStudent();
        newUserDTO = studentManagementSB.getStudentById(idUser);
    }

    private void getAllCourseFromStudent() {
        ListCourseDTO listCourseDTO = studentManagementSB.getCoursesFromStudent(idUser);
        listCourseFromStudent = new LinkedList<>(listCourseDTO.getListCourse());
        listCourseDTO = courseManagementSB.getAllCourseOfStudentInUniversity(idUser);
        listCourseFromAll = new LinkedList<>(listCourseDTO.getListCourse());        
    }

    public void enrollStudentOnCourse(Long idCourse) {
        AnswerDTO answerDTO = studentManagementSB.enrollStudentOnCourse(idUser, idCourse);
        UtilitiesMB.showFeedback(answerDTO);
        ListCourseDTO listCourseDTO = studentManagementSB.getCoursesFromStudent(idUser);
        listCourseFromStudent = new LinkedList<>(listCourseDTO.getListCourse());
        System.out.println("count " + listCourseFromStudent.size());
    }

    /**
     * **********
     */
    /**
     * **********
     */
    public LinkedList<CourseDTO> getListCourseFromStudent() {
        return listCourseFromStudent;
    }

    public void setListCourseFromStudent(LinkedList<CourseDTO> listCourseFromStudent) {
        this.listCourseFromStudent = listCourseFromStudent;
    }

    /**
     *
     * @return
     */
    public String getSelectedCourse() {
        return selectedCourse;
    }

    /**
     *
     * @param selectedCourse
     */
    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public List<Course> getFilteredListCourseFromStudent() {
        return filteredListCourseFromStudent;
    }

    public void setFilteredListCourseFromStudent(List<Course> filteredListCourseFromStudent) {
        this.filteredListCourseFromStudent = filteredListCourseFromStudent;
    }

    public LinkedList<CourseDTO> getListCourseFromAll() {
        return listCourseFromAll;
    }

    public void setListCourseFromAll(LinkedList<CourseDTO> listCourseFromAll) {
        this.listCourseFromAll = listCourseFromAll;
    }

    public CourseDTO getSelectedCourseFromStudent() {
        return selectedCourseFromStudent;
    }

    public void setSelectedCourseFromStudent(CourseDTO selectedCourseFromStudent) {
        this.selectedCourseFromStudent = selectedCourseFromStudent;
    }

    public CourseDTO getSelectedCourseFromAll() {
        return selectedCourseFromAll;
    }

    public void setSelectedCourseFromAll(CourseDTO selectedCourseFromAll) {
        this.selectedCourseFromAll = selectedCourseFromAll;
    }

    public NewUserDTO getNewUserDTO() {
        return newUserDTO;
    }

    public void setNewUserDTO(NewUserDTO newUserDTO) {
        this.newUserDTO = newUserDTO;
    }

    public List<Course> getFilteredListCourseFromAll() {
        return filteredListCourseFromAll;
    }

    public void setFilteredListCourseFromAll(List<Course> filteredListCourseFromAll) {
        this.filteredListCourseFromAll = filteredListCourseFromAll;
    }
}
