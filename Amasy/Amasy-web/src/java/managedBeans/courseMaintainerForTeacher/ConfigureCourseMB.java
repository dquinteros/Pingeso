/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainerForTeacher;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "configureCourseMB")
@RequestScoped
public class ConfigureCourseMB {
    @EJB
    private CourseManagementSBLocal courseManagementSB;
    
    @Inject 
    private CourseMaintainerOfTeacherConversationalMB courseMaintainerOfTeacherConversation;

    private CourseDTO course;
    private String minutesBeforeAux;
    private String minutesAfterAux;
    
    public ConfigureCourseMB() {
    }
    
    @PostConstruct
    public void init() {
        course = courseManagementSB.getCourseById(courseMaintainerOfTeacherConversation.getIdCourse());
        minutesAfterAux = String.valueOf(course.getMinutesAfterClassStart());
        minutesBeforeAux = String.valueOf(course.getMinutesBeforeClassStart());
    }
    
    public void configureAssistanceTimebox(){
        AnswerDTO ans = new AnswerDTO();
        course.setMinutesAfterClassStart(Integer.parseInt(minutesAfterAux));
        course.setMinutesBeforeClassStart(Integer.parseInt(minutesBeforeAux));
        ans = courseManagementSB.configureAssistanceTimebox(course);
        UtilitiesMB.showFeedback(ans);
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public String getMinutesAfterAux() {
        return minutesAfterAux;
    }

    public void setMinutesAfterAux(String minutesAfterAux) {
        this.minutesAfterAux = minutesAfterAux;
    }

    public String getMinutesBeforeAux() {
        return minutesBeforeAux;
    }

    public void setMinutesBeforeAux(String minutesBeforeAux) {
        this.minutesBeforeAux = minutesBeforeAux;
    }
}
