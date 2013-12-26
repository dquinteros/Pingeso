/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class BlockClassDTO {

    private Date date;
    private Long dayBlockClass;
    private Long timeBlockClass;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public BlockClassDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getDayBlockClass() {
        return dayBlockClass;
    }

    public void setDayBlockClass(Long dayBlockClass) {
        this.dayBlockClass = dayBlockClass;
    }

    public Long getTimeBlockClass() {
        return timeBlockClass;
    }

    public void setTimeBlockClass(Long timeBlockClass) {
        this.timeBlockClass = timeBlockClass;
    }
}
