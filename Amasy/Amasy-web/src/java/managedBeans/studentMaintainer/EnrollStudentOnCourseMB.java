/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.studentMaintainer;

import DTOs.ListCourseDTO;
import entity.Course;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.studentManagement.StudentManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "enrollStudentOnCourseMB")
@RequestScoped
public class EnrollStudentOnCourseMB {

    /**
     * Creates a new instance of EnrollStudentOnCourseMB
     */
    @EJB
    private StudentManagementSBLocal studentManagementSB;
    @Inject
    private StudentMaintainerConversationalMB studentMaintainerConversation;
    private LinkedList<Course> listCourseFromStudent;
    private Long userId;
    private LinkedList<Course> listCourse;
    private String selectedCourse;
    
    public EnrollStudentOnCourseMB() {
    }

    @PostConstruct
    private void init() {
        userId = studentMaintainerConversation.getIdUser();
        getAllCourseFromStudent();
    }
    
    private void getAllCourseFromStudent(){
        ListCourseDTO listCourseDTO = studentManagementSB.getCoursesFromStudent(userId); 
        listCourseFromStudent = new LinkedList<>(listCourseDTO.getListCourse());

        //listCourseDTO = studentManagementSB.getCoursesNotFromStudent(userId);
        listCourse  = new LinkedList<>(listCourseDTO.getListCourse());
        
        ////////////////////////////////////
    }

    public LinkedList<Course> getListCourseFromStudent() {
        return listCourseFromStudent;
    }

    public void setListCourseFromStudent(LinkedList<Course> listCourseFromStudent) {
        this.listCourseFromStudent = listCourseFromStudent;
    }

    public LinkedList<Course> getListCourse() {
        return listCourse;
    }

    public void setListCourse(LinkedList<Course> listCourse) {
        this.listCourse = listCourse;
    }      

    public String getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
    }        
    
}
