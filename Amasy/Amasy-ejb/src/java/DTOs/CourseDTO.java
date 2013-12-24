/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entity.Course;
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

    /**
     *
     * @param course
     */
    public CourseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.level = course.getLevel();
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

    
    
}
