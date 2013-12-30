/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainerForTeacher;

import DTOs.AssistanceListCourseDTO;
import DTOs.AssistanceListCourseRowDTO;
import DTOs.AssistanceListCourseTitleDTO;
import DTOs.AssistanceListCourseUnitDTO;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.LoginSessionMB;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "viewAssistanceOfCourseMB")
@RequestScoped
public class ViewAssistanceOfCourseMB {

    /**
     * Creates a new instance of ViewAssistanceOfCourseMB
     */
    @EJB
    private CourseManagementSBLocal courseManagementSB;
    @Inject
    private CourseMaintainerOfTeacherConversationalMB courseMaintainerOfTeacherConversation;
    private Long idCourse;
    private AssistanceListCourseDTO assistanceListCourse;

    public ViewAssistanceOfCourseMB() {
    }

    @PostConstruct
    void Init() {
        idCourse = courseMaintainerOfTeacherConversation.getIdCourse();
        assistanceListCourse = courseManagementSB.assistanceListCourse(idCourse);
    }

    public AssistanceListCourseDTO getAssistanceListCourse() {
        return assistanceListCourse;
    }

    public void setAssistanceListCourse(AssistanceListCourseDTO assistanceListCourse) {
        this.assistanceListCourse = assistanceListCourse;
    }
}
