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
import javax.persistence.Temporal;

/**
 *
 * @author Pingeso
 */

@NamedQueries( {
    @NamedQuery(name = "Assistance.findStudentAssistance", query = "SELECT COUNT(a) FROM Assistance a WHERE a.blockClass.id = :idBlockClass AND a.student.id = :idStudent")
})

@Entity
public class Assistance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    @ManyToOne
    private Student student;
    
    @ManyToOne
    private BlockClass blockClass;
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    

    public BlockClass getBlockClass() {
        return blockClass;
    }

    public void setBlockClass(BlockClass blockClass) {
        this.blockClass = blockClass;
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

    @Override
    public String toString() {
        return "entity.Asistance[ id=" + id + " ]";
    }
    
}
