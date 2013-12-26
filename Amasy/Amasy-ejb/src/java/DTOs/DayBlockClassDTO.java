/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entity.DayBlockClass;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class DayBlockClassDTO {
    private Long id;
    private String abbreviation;
    private String day;

    public DayBlockClassDTO(){
    }
    
    public DayBlockClassDTO(DayBlockClass dayBLockClass){
        this.id = dayBLockClass.getId();
        this.abbreviation = dayBLockClass.getAbbreviation();
        this.day = dayBLockClass.getDay();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }        
}
