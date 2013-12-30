/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entity.Assistance;
import entity.AssistanceState;
import entity.BlockClass;
import entity.DayBlockClass;
import entity.TimeBlockClass;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class AssistanceDTO {

    private Long id;
    private TimeBlockClassDTO timeBlockClassDTO;
    private DayBlockClassDTO dayBlockClassDTO;
    private Date date;
    private String state;
    private String dateString;

    public AssistanceDTO() {
    }

    public AssistanceDTO(BlockClass blockClass, TimeBlockClass timeBlockClass, DayBlockClass dayBlockClass, AssistanceState assistanceState) {
        date = blockClass.getDate();
        timeBlockClassDTO = new TimeBlockClassDTO(timeBlockClass);
        dayBlockClassDTO = new DayBlockClassDTO(dayBlockClass);
        state = assistanceState.getName();
        dateString = dateFormat(date);
    }

    public AssistanceDTO(Assistance assistance, BlockClass blockClass, TimeBlockClass timeBlockClass, DayBlockClass dayBlockClass, AssistanceState assistanceState) {
        id = assistance.getId();
        timeBlockClassDTO = new TimeBlockClassDTO(timeBlockClass);
        dayBlockClassDTO = new DayBlockClassDTO(dayBlockClass);
        date = blockClass.getDate();
        state = assistanceState.getName();
        dateString = dateFormat(date);
    }

    private static String dateFormat(Date date) {
        if (null == date) {
            return "";
        } else {
            String formato = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            return dateFormat.format(date);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimeBlockClassDTO getTimeBlockClassDTO() {
        return timeBlockClassDTO;
    }

    public void setTimeBlockClassDTO(TimeBlockClassDTO timeBlockClassDTO) {
        this.timeBlockClassDTO = timeBlockClassDTO;
    }

    public DayBlockClassDTO getDayBlockClassDTO() {
        return dayBlockClassDTO;
    }

    public void setDayBlockClassDTO(DayBlockClassDTO dayBlockClassDTO) {
        this.dayBlockClassDTO = dayBlockClassDTO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
