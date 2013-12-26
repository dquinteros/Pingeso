/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entity.TimeBlockClass;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class TimeBlockClassDTO {
    private Long id;
    private String startHour;
    private String endHour;
    private String abbreviation;

    public TimeBlockClassDTO(){
    }
    
    public TimeBlockClassDTO(TimeBlockClass timeBlockClass){
        this.id = timeBlockClass.getId();
        this.startHour = timeBlockClass.getStartHour();
        this.endHour = timeBlockClass.getEndHour();
        this.abbreviation = timeBlockClass.getAbbreviation();        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    
}
