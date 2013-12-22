/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.studentMaintainer;

import DTOs.CourseDTO;
import DTOs.ListCourseDTO;
import entity.Course;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import sessionBeans.courseManagement.CourseManagementSBLocal;
import sessionBeans.studentManagement.StudentManagementSBLocal;

@Named(value = "enrollStudentOnCourseMB")
@RequestScoped
public class EnrollStudentOnCourseMB {
    @EJB
    private CourseManagementSBLocal courseManagementSB;

    @EJB
    private StudentManagementSBLocal studentManagementSB;
    
    @Inject
    private StudentMaintainerConversationalMB studentMaintainerConversation;
    private LinkedList<Course> listCourseFromStudent;
    private Long userId;
    private LinkedList<CourseDTO> listCourse;
    private String selectedCourse;
    
    private Course selectedCourseFromStudent;
    private LinkedList<Course> filteredListCourseFromStudent;
    
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

        //listCourseDTO = courseManagementSB.getAllCourse();
        listCourse  = courseManagementSB.getAllCourse();
        
        ////////////////////////////////////
    }

    public LinkedList<Course> getListCourseFromStudent() {
        return listCourseFromStudent;
    }

    public void setListCourseFromStudent(LinkedList<Course> listCourseFromStudent) {
        this.listCourseFromStudent = listCourseFromStudent;
    }

    public LinkedList<CourseDTO> getListCourse() {
        return listCourse;
    }

    public void setListCourse(LinkedList<CourseDTO> listCourse) {
        this.listCourse = listCourse;
    }    

    public String getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(String selectedCourse) {
        this.selectedCourse = selectedCourse;
    }        

    public LinkedList<Course> getFilteredListCourseFromStudent() {
        return filteredListCourseFromStudent;
    }

    public void setFilteredListCourseFromStudent(LinkedList<Course> filteredListCourseFromStudent) {
        this.filteredListCourseFromStudent = filteredListCourseFromStudent;
    }

    public Course getSelectedCourseFromStudent() {
        return selectedCourseFromStudent;
    }

    public void setSelectedCourseFromStudent(Course selectedCourseFromStudent) {
        this.selectedCourseFromStudent = selectedCourseFromStudent;
    }
    
    
    
}
