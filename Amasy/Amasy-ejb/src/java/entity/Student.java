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
import javax.persistence.ManyToMany;
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
@NamedQueries({
    @NamedQuery(name = "Student.findByIdUser", query = "SELECT a FROM Student a WHERE a.user.id = :idUser AND a.user.userStatus = true"),
    @NamedQuery(name = "Student.getAllStudentUserInfo", query = "SELECT u.user FROM Student u WHERE u.user.userStatus = true"),
    @NamedQuery(name = "Student.getListCourseFromUser", query = "SELECT s.listCourse FROM Student s WHERE s.user.id = :idUser"),
    @NamedQuery(name = "Student.getStundentOfCourse", query = "SELECT c.listStudent FROM Course c WHERE c.id = :idCourse"),
    @NamedQuery(name = "Student.getAllStudentFromCourse", query = "SELECT c.listStudent FROM Course c WHERE c.id = :idCourse")
})
@Entity
public class Student implements Serializable {
    @ManyToOne
    private Course course;

    @ManyToMany
    private List<Course> listCourse;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date enrollYear;


    @OneToMany(mappedBy = "student")
    private List<Assistance> assistance;
    @OneToOne
    private User user;
    

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
    public Date getEnrollYear() {
        return enrollYear;
    }

    /**
     *
     * @param enrollYear
     */
    public void setEnrollYear(Date enrollYear) {
        this.enrollYear = enrollYear;
    }

    public List<Assistance> getAssistance() {
        return assistance;
    }

    public void setAssistance(List<Assistance> assistance) {
        this.assistance = assistance;
    }

    /**
     *
     * @return
     */
    public Date getIncomeYear() {
        return enrollYear;
    }

    /**
     *
     * @param incomeYear
     */
    public void setIncomeYear(Date incomeYear) {
        this.enrollYear = incomeYear;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
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
        return "entity.Student[ id=" + id + " ]";
    }
}
