/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import DTOs.UserDTO;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import managedBeans.UtilitiesMB;
import sessionBeans.courseManagement.CourseManagementSBLocal;
import sessionBeans.teacherManagement.TeacherManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "addCourseMB")
@RequestScoped
public class AddCourseMB {
    @EJB
    private TeacherManagementSBLocal teacherManagementSB;
    @EJB
    private CourseManagementSBLocal courseManagementSB;
    
  
    private CourseDTO newCourse;
    private LinkedList<UserDTO> userList;
    private UserDTO user;

    /**
     * Creates a new instance of AddCourseMB
     */
    public AddCourseMB() {
    }
    
    /**
     *
     */
    @PostConstruct
    public void init() {
        newCourse = new CourseDTO();
        userList = teacherManagementSB.getAllTeacher();
    }
    
    /**
     *
     */
    public void insertNewCourse(){
        AnswerDTO r = new AnswerDTO();
        newCourse.setLevel(newCourse.getLevel());
        r = courseManagementSB.insertNewCourse(newCourse);
        UtilitiesMB.showFeedback(r); 
    }
    
        /**
     *
     * @return
     */
    public CourseDTO getNewCourse() {
        return newCourse;
    }

    /**
     *
     * @param newCourse
     */
    public void setNewCourse(CourseDTO newCourse) {
        this.newCourse = newCourse;
    }

    public LinkedList<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(LinkedList<UserDTO> userList) {
        this.userList = userList;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
    
}
