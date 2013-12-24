/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Pingeso
 */
@NamedQueries( {
    @NamedQuery(name = "BlockClass.findStudentByBlockClass", query = "SELECT b.course.listStudent FROM BlockClass b WHERE b.id = :idBlockClass")

})
@Entity
public class BlockClass implements Serializable {
    //@Column(nullable = false)
    @ManyToOne
    private TimeBlockClass timeBlockClass;
  
    @ManyToOne
    private Course course;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    private boolean done;
    private String comment;          
      
    @OneToMany(mappedBy = "blockClass")
    private List<Assistance> listAssistance;
    
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
    public boolean isDone() {
        return done;
    }

    /**
     *
     * @param done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     *
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Course getCourse() {
        return course;
    }

    /**
     *
     * @param course
     */
    public void setCourse(Course course) {
        this.course = course;
    }   

    public List<Assistance> getListAssistance() {
        return listAssistance;
    }

    public void setListAssistance(List<Assistance> listAssistance) {
        this.listAssistance = listAssistance;
    }

    public TimeBlockClass getTimeBlockClass() {
        return timeBlockClass;
    }

    public void setTimeBlockClass(TimeBlockClass timeBlockClass) {
        this.timeBlockClass = timeBlockClass;
    }        
    
    /**
     *
     * @return
     */
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
        if (!(object instanceof BlockClass)) {
            return false;
        }
        BlockClass other = (BlockClass) object;
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
        return "entity.BlockClass[ id=" + id + " ]";
    }
    
}
