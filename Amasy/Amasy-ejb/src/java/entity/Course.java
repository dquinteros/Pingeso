/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
@NamedQueries( {
    @NamedQuery(name="Course.getAllStudentsOfCourse", query="SELECT e FROM Student e WHERE e.user.userStatus = true"),
    @NamedQuery(name="Course.getAllCourses", query="SELECT c FROM Course c"),
    @NamedQuery(name="Course.countCourseByName", query="SELECT COUNT(c) FROM Course c WHERE c.name = :name")
})
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String level;

    @OneToMany
    private List<BlockClass> listBlockClass;
    @ManyToMany
    private List<Student> listStudent;
    @OneToMany
    private List<Teacher> listTeacher;
    
    /**
     *
     * @return
     */
    public List<BlockClass> getListBlockClass() {
        return listBlockClass;
    }

    /**
     *
     * @param listBlockClass
     */
    public void setListBlockClass(List<BlockClass> listBlockClass) {
        this.listBlockClass = listBlockClass;
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
    public List<Student> getListStudent() {
         return listStudent;
     }
 
     /**
     *
     * @param listStudent
     */
    public void setListStudent(List<Student> listStudent) {
         this.listStudent = listStudent;
     }
 

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getLevel() {
        return level;
    }

    /**
     *
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }


    /**
     *
     * @return
     */
    public List<Teacher> getListTeacher() {
        return listTeacher;
    }

    /**
     *
     * @param listTeacher
     */
    public void setListTeacher(List<Teacher> listTeacher) {
        this.listTeacher = listTeacher;
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
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
        return "entity.Course[ id=" + id + " ]";
    }
    
}
