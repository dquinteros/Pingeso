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

/**
 *
 * @author Pingeso
 */
@Entity
@NamedQueries( {
    @NamedQuery(name="Course.getAllStudentsOfCourse", query="SELECT e FROM Student e"),
    @NamedQuery(name="Course.deleteStudentById", query="DELETE FROM Course c WHERE c.id = :idStudent")
})
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int level;

    @OneToMany(mappedBy = "course")
    private List<BlockClass> listBlockClass;    
    @ManyToMany
    private List<Student> listStudent;    
    @OneToMany
    private List<Teacher> listTeacher;

    public List<BlockClass> getListBlockClass() {
        return listBlockClass;
    }

    public void setListBlockClass(List<BlockClass> listBlockClass) {
        this.listBlockClass = listBlockClass;
    }        
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Student> getListStudent() {
        return listStudent;
    }

    public void setListStudent(List<Student> listStudent) {
        this.listStudent = listStudent;
    }

    public List<Teacher> getListTeacher() {
        return listTeacher;
    }

    public void setListTeacher(List<Teacher> listTeacher) {
        this.listTeacher = listTeacher;
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Course[ id=" + id + " ]";
    }
    
}
