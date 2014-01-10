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
import javax.persistence.OneToMany;

/**
 *
 * @author Pingeso
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Course.getAllStudentsOfCourse", query = "SELECT e FROM Student e WHERE e.user.userStatus = true"),
    @NamedQuery(name = "Course.getAllCourses", query = "SELECT c FROM Course c WHERE c.university.universityStatus = true"),
    @NamedQuery(name = "getAllCourseOfStudentInUniversity", query = "SELECT c FROM Course c WHERE c.university.id = :idUniversity"),
    @NamedQuery(name = "Course.countCourseByName", query = "SELECT COUNT(c) FROM Course c WHERE c.name = :name AND c.university.universityStatus = true"),
    @NamedQuery(name = "Course.getAllCoursesOfTeacher", query = "SELECT t.listCourse FROM Teacher t WHERE t.user.id = :idUser")
})
public class Course implements Serializable {

    @ManyToOne
    private University university;
    @OneToMany(mappedBy = "course")
    private List<GroupStudentPerCourse> listGroup;
    @ManyToOne
    private Teacher teacher;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String level;
    private int minutesAfterClassStart;
    private int minutesBeforeClassStart;
    @OneToMany(mappedBy = "course")
    private List<BlockClass> listBlockClass;
    @ManyToMany(mappedBy = "listCourse")
    private List<Student> listStudent;

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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getMinutesAfterClassStart() {
        return minutesAfterClassStart;
    }

    public void setMinutesAfterClassStart(int minutesAfterClassStart) {
        this.minutesAfterClassStart = minutesAfterClassStart;
    }

    public int getMinutesBeforeClassStart() {
        return minutesBeforeClassStart;
    }

    public void setMinutesBeforeClassStart(int minutesBeforeClassStart) {
        this.minutesBeforeClassStart = minutesBeforeClassStart;
    }

    public List<GroupStudentPerCourse> getListGroup() {
        return listGroup;
    }

    public void setListGroup(List<GroupStudentPerCourse> listGroup) {
        this.listGroup = listGroup;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
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
