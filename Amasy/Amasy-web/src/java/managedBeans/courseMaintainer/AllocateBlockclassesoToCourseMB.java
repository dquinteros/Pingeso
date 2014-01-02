/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import DTOs.AnswerDTO;
import DTOs.BlockClassDTO;
import DTOs.BlockClassListDTO;
import DTOs.CourseDTO;
import DTOs.DayBlockClassDTO;
import DTOs.DayBlockClassListDTO;
import DTOs.TimeBlockClassDTO;
import DTOs.TimeBlockClassListDTO;
import entity.Course;
import java.util.Date;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import sessionBeans.courseManagement.CourseManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "allocateBlockclassesoToCourseMB")
@RequestScoped
public class AllocateBlockclassesoToCourseMB {

    @EJB
    private CourseManagementSBLocal courseManagementSBLocal;
    @Inject
    private CourseMaintainerConversationalMB courseMaintainerConversationalMB;
    private Long idCourse;
    private Date date;
    private String day;
    private String time;
    private DayBlockClassListDTO listDayBlockClass;
    private TimeBlockClassListDTO listTimeBlockClass;
    private LinkedList<String> listHour;
    private LinkedList<DateTimeBlockClassCourse> listDateTimeBlockClassCourse;
    private LinkedList<DateTimeBlockClassCourse> filteredListDateTimeBlockClassCourse;
    private CourseDTO course;

    /**
     * Creates a new instance of AllocateBlockclassesoToCourseMB
     */
    public AllocateBlockclassesoToCourseMB() {
    }

    @PostConstruct
    public void init() {
        idCourse = courseMaintainerConversationalMB.getIdCourse();
        listDayBlockClass = courseManagementSBLocal.getAllDayBlockClassDTO();
        listTimeBlockClass = courseManagementSBLocal.getAllTimeBlockClass();
        listHour = generateListHour(listTimeBlockClass);
        generateListDateTimeBlockClassCourse();
        course = courseManagementSBLocal.getCourseById(idCourse);
    }    
    
    private void generateListDateTimeBlockClassCourse() {
        BlockClassListDTO blockClassListDTO = courseManagementSBLocal.getAllBlockClassOfCourse(idCourse);
        listDateTimeBlockClassCourse = new LinkedList<>();
        for (BlockClassDTO it : blockClassListDTO.getListBlockClass()) {
            listDateTimeBlockClassCourse.add(new DateTimeBlockClassCourse(it));
        }
    }

    public void addDateTimeBlockClassCourse() {
        if (!existBlockClass(time, date)) {
            saveBlockClassOfCourse(new DateTimeBlockClassCourse(time, date));
        } else {
            UtilitiesMB.showFeedback(new AnswerDTO(130));
        }
    }

    private boolean existBlockClass(String time, Date date) {
        String[] split = time.split(":");
        Long minute = Long.parseLong(split[1]) * 60L * 1000L;
        Long hour = Long.parseLong(split[0]) * 60L * 60L * 1000L;
        date = new Date(date.getTime() + hour + minute);
        for (DateTimeBlockClassCourse it : listDateTimeBlockClassCourse) {
            if (it.getDate().getTime() == date.getTime()) {
                return true;
            }
        }
        return false;
    }

    private LinkedList<String> generateListHour(TimeBlockClassListDTO listTimeBlockClass) {
        LinkedList<String> listHour = new LinkedList<>();
        for (TimeBlockClassDTO it : listTimeBlockClass.getListBlockClass()) {
            listHour.add(it.getStartHour());
        }
        return listHour;
    }

    public void saveBlockClassOfCourse(DateTimeBlockClassCourse dateTimeBlockClassCourse) {
        BlockClassDTO blockClass = new BlockClassDTO();
        blockClass.setDate(dateTimeBlockClassCourse.getDate());
        blockClass.setDayBlockClass(getIdDayBlockClass(dateTimeBlockClassCourse.getDay()));
        blockClass.setTimeBlockClass(getIdTimeBlockClass(dateTimeBlockClassCourse.getTime()));
        AnswerDTO answer = courseManagementSBLocal.allocateBlockclassesoToCourse(idCourse, blockClass);
        if(answer.getIdError()==0){
            generateListDateTimeBlockClassCourse();
        }
        UtilitiesMB.showFeedback(answer);
    }

    private Long getIdDayBlockClass(String day) {
        for (DayBlockClassDTO it : listDayBlockClass.getListBlockClass()) {
            if (it.getDay().equals(day)) {
                return it.getId();
            }
        }
        return -1L;
    }

    private Long getIdTimeBlockClass(String hour) {
        for (TimeBlockClassDTO it : listTimeBlockClass.getListBlockClass()) {
            if (it.getStartHour().equals(hour)) {
                return it.getId();
            }
        }
        return -1L;
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public CourseMaintainerConversationalMB getCourseMaintainerConversationalMB() {
        return courseMaintainerConversationalMB;
    }

    public void setCourseMaintainerConversationalMB(CourseMaintainerConversationalMB courseMaintainerConversationalMB) {
        this.courseMaintainerConversationalMB = courseMaintainerConversationalMB;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LinkedList<String> getListHour() {
        return listHour;
    }

    public void setListHour(LinkedList<String> listHour) {
        this.listHour = listHour;
    }

    public LinkedList<DateTimeBlockClassCourse> getListDateTimeBlockClassCourse() {
        return listDateTimeBlockClassCourse;
    }

    public void setListDateTimeBlockClassCourse(LinkedList<DateTimeBlockClassCourse> listDateTimeBlockClassCourse) {
        this.listDateTimeBlockClassCourse = listDateTimeBlockClassCourse;
    }

    public LinkedList<DateTimeBlockClassCourse> getFilteredListDateTimeBlockClassCourse() {
        return filteredListDateTimeBlockClassCourse;
    }

    public void setFilteredListDateTimeBlockClassCourse(LinkedList<DateTimeBlockClassCourse> filteredListDateTimeBlockClassCourse) {
        this.filteredListDateTimeBlockClassCourse = filteredListDateTimeBlockClassCourse;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
}
