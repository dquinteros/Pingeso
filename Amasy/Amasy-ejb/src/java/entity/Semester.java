/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Pingeso
 */
@Entity
public class Semester implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private University university;
    @OneToMany(mappedBy = "semester")
    private List<Course> listCourse;
    
    private boolean semesterState;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date startSemesterDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date endSemesterDate;

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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<Course> getListCourse() {
        return listCourse;
    }

    public void setListCourse(List<Course> listCourse) {
        this.listCourse = listCourse;
    }

    public boolean isSemesterState() {
        return semesterState;
    }

    public void setSemesterState(boolean semesterState) {
        this.semesterState = semesterState;
    }

    public Date getStartSemesterDate() {
        return startSemesterDate;
    }

    public void setStartSemesterDate(Date startSemesterDate) {
        this.startSemesterDate = startSemesterDate;
    }

    public Date getEndSemesterDate() {
        return endSemesterDate;
    }

    public void setEndSemesterDate(Date endSemesterDate) {
        this.endSemesterDate = endSemesterDate;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semester)) {
            return false;
        }
        Semester other = (Semester) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Semester[ id=" + id + " ]";
    }
}
