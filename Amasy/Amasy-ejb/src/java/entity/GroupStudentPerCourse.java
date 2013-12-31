package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Pingeso
 */
@Entity
@NamedQueries( {
    @NamedQuery(name="GroupStudentPerCourse.countGroupOfCourseByGroupName", query="SELECT COUNT(g) FROM GroupStudentPerCourse g WHERE g.name = :groupName AND g.course.id = :idCourse AND g.groupStatus = true"),
    @NamedQuery(name="GroupStudentPerCourse.getGroupOfCourseByGroupName", query="SELECT g FROM GroupStudentPerCourse g WHERE g.name = :groupName AND g.course.id = :idCourse AND g.groupStatus = true"),
    @NamedQuery(name="GroupStudentPerCourse.getAllGroupsOfCourseByIdCourse",query="SELECT g FROM GroupStudentPerCourse g WHERE g.course.id = :idCourse AND g.groupStatus = true"),
    @NamedQuery(name="GroupStudentPerCourse.getAllStudentsWithGroup",query="SELECT g.listStudent FROM GroupStudentPerCourse g WHERE g.groupStatus = true AND g.course.id = :idCourse")
})

public class GroupStudentPerCourse implements Serializable {
    @ManyToOne
    private Course course;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean groupStatus;
    
    @ManyToMany(mappedBy = "listGroup")
    private List<Student> listStudent;

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public boolean isGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(boolean groupStatus) {
        this.groupStatus = groupStatus;
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
        if (!(object instanceof GroupStudentPerCourse)) {
            return false;
        }
        GroupStudentPerCourse other = (GroupStudentPerCourse) object;
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
        return "entity.group[ id=" + id + " ]";
    }
    
}
