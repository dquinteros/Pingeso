/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entity.Course;
import entity.User;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class CourseDTO {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private Long id;
    private String name;
    private String level;
    private int minutesAfterClassStart;
    private int minutesBeforeClassStart;
    private String nameUniversity;
    private UserDTO teacher;
    private Long idUniversity;

    /**
     *
     * @param course
     */
    public CourseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.level = course.getLevel();
        this.minutesAfterClassStart = course.getMinutesAfterClassStart();
        this.minutesBeforeClassStart = course.getMinutesBeforeClassStart();
        this.teacher = new UserDTO();
        this.teacher.setFirstName("");
        this.teacher.setLastName("");
    }

    public CourseDTO(Course course, User teacher) {
        this.id = course.getId();
        this.name = course.getName();
        this.level = course.getLevel();
        this.minutesAfterClassStart = course.getMinutesAfterClassStart();
        this.minutesBeforeClassStart = course.getMinutesBeforeClassStart();
        if (teacher != null) {
            this.teacher = new UserDTO(teacher);
        } else {
            this.teacher = new UserDTO();
            this.teacher.setFirstName("");
            this.teacher.setLastName("");
        }
    }

    /**
     *
     */
    public CourseDTO() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNameUniversity() {
        return nameUniversity;
    }

    public void setNameUniversity(String nameUniversity) {
        this.nameUniversity = nameUniversity;
    }

    public UserDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(UserDTO teacher) {
        this.teacher = teacher;
    }

    public Long getIdUniversity() {
        return idUniversity;
    }

    public void setIdUniversity(Long idUniversity) {
        this.idUniversity = idUniversity;
    }
}
