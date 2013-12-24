/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Pingeso
 */
@Named(value = "allocateBlockclassesoToCourseMB")
@RequestScoped
public class AllocateBlockclassesoToCourseMB {

    @Inject
    private CourseMaintainerConversationalMB courseMaintainerConversationalMB;
    private Long idCourse;
    private Date startDate;
    private Date endDate;

    /**
     * Creates a new instance of AllocateBlockclassesoToCourseMB
     */
    public AllocateBlockclassesoToCourseMB() {
    }

    @PostConstruct
    public void init() {
        idCourse = courseMaintainerConversationalMB.getIdCourse();
        System.out.println("idcourse " + idCourse);
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
