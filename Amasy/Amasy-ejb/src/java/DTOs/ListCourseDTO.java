/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entity.Course;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class ListCourseDTO {
    private Collection<Course> listCourse;
    private AnswerDTO answerDTO;

    public ListCourseDTO(){
    }
    
    public ListCourseDTO(Collection<Course> listCourse, AnswerDTO answerDTO){
        this.listCourse = listCourse;
        this.answerDTO = answerDTO;
    }
    
    public Collection<Course> getListCourse() {
        return listCourse;
    }

    public void setListCourse(Collection<Course> listCourse) {
        this.listCourse = listCourse;
    }

    public AnswerDTO getAnswerDTO() {
        return answerDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }        

}
