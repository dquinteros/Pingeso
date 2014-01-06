package DTOs;

import entity.University;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class UniversityDTO {

    private Long id;
    private String name;
    private int rut;
    private String direction;

    public UniversityDTO() {
    }
    
    public UniversityDTO(University university) {
        this.direction = university.getDirection();
        this.id = university.getId();
        this.name = university.getName();
        this.rut = university.getRut();
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

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
