/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Pingeso
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Teacher.getAllTeacherUserInfo", query="SELECT u.user FROM Teacher u WHERE u.user.userStatus = true")
    //@NamedQuery(name="Teacher.getStundentAttendanceFromCourse", query="SELECT c.listStudent FROM Course c WHERE c.id = :course")
})
public class Teacher implements Serializable {
    @OneToOne
    private Course course;
    @OneToOne
    private JustifiedAudit justifiedAudit;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;
    

    @OneToMany(mappedBy = "teacher")
    private List<Course> listCourse;

    /**
     *
     * @return
     */
    public List<Course> getListCourse() {
        return listCourse;
    }

    /**
     *
     * @param listCourse
     */
    public void setListCourse(List<Course> listCourse) {
        this.listCourse = listCourse;
    }        
    
    
    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public JustifiedAudit getJustifiedAudit() {
        return justifiedAudit;
    }

    public void setJustifiedAudit(JustifiedAudit justifiedAudit) {
        this.justifiedAudit = justifiedAudit;
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
        if (!(object instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) object;
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
        return "entity.Teacher[ id=" + id + " ]";
    }
    
}
