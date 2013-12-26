/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import DTOs.BlockClassDTO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import managedBeans.UtilitiesMB;

/**
 *
 * @author Pingeso
 */
public class DateTimeBlockClassCourse {

    private String time;
    private Date date;
    private String day;
    private String dateString;
    
    public DateTimeBlockClassCourse() {
    }
    
    public DateTimeBlockClassCourse(BlockClassDTO listBlockClassDTO) { 
        date = listBlockClassDTO.getDate();
        day = getDateName(date);
        String hour = ""+date.getHours();
        String minute;
        if(date.getHours()<10){
            minute = "0"+date.getMinutes();
        }else{
            minute = ""+date.getMinutes();
        }
        time = hour+":"+minute;
        
        this.dateString = UtilitiesMB.dateFormat(date);     
    }
    
    public DateTimeBlockClassCourse(String time, Date date) {
        String[] split = time.split(":");
        Long minute = Long.parseLong(split[1])*60L*1000L;
        Long hour = Long.parseLong(split[0])*60L*60L*1000L;        
        this.date = new Date(date.getTime()+hour+minute);
        this.time = time;
        this.dateString = UtilitiesMB.dateFormat(date);     
        this.day = getDateName(date);
    }
    
    private String getDateName(Date date){
        switch(date.getDay()){
            case 0:
                return "Domingo";   
            case 1:
                return "Lunes";
            case 2:
                return "Martes";
            case 3:
                return "Miércoles";
            case 4:
                return "Jueves";
            case 5:
                return "Viernes";
            case 6:
                return "Sábado";
            default: return "";
        }
    }    
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
    
    
}
