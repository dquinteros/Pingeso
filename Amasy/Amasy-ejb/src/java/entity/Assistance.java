/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Pingeso
 */
@NamedQueries({
    @NamedQuery(name = "Assistance.findStudentAssistance", query = "SELECT COUNT(a) FROM Assistance a WHERE a.blockClass.id = :idBlockClass AND a.student.id = :idStudent AND a.student.user.userStatus = true"),
    @NamedQuery(name = "Assistance.findAssistanceOfBlockIdClassAndIdStudent", query = "SELECT a FROM Assistance a WHERE a.blockClass.id = :idBlockClass AND a.student.id = :idStudent AND a.student.user.userStatus = true"),
    @NamedQuery(name = "Assistance.getAssistanceStudentToCourse", query = "SELECT a FROM Assistance a WHERE a.blockClass.course.id = :idCourse AND a.student.user.id = :idUser AND a.student.user.userStatus = true")
})
@Entity
public class Assistance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @ManyToOne
    private BlockClass blockClass;
    
    @ManyToOne
    private Student student;
    @OneToOne
    private JustifiedAudit justifiedAudit;
    private static final long serialVersionUID = 1L;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    private AssistanceState state;

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public AssistanceState getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(AssistanceState state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public Student getStudent() {
        return student;
    }

    /**
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    public JustifiedAudit getJustifiedAudit() {
        return justifiedAudit;
    }

    public void setJustifiedAudit(JustifiedAudit justifiedAudit) {
        this.justifiedAudit = justifiedAudit;
    }

    public BlockClass getBlockClass() {
        return blockClass;
    }

    public void setBlockClass(BlockClass blockClass) {
        this.blockClass = blockClass;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assistance)) {
            return false;
        }
        Assistance other = (Assistance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "entity.Assistance[ id=" + id + " ]";
    }
}
