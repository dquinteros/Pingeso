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
    private Collection<CourseDTO> listCourse;
    private AnswerDTO answerDTO;

    /**
     *
     */
    public ListCourseDTO(){
    }
    
    /**
     *
     * @param listCourse
     * @param answerDTO
     */
    public ListCourseDTO(Collection<CourseDTO> listCourse, AnswerDTO answerDTO){
        this.listCourse = listCourse;
        this.answerDTO = answerDTO;
    }

    public Collection<CourseDTO> getListCourse() {
        return listCourse;
    }

    public void setListCourse(Collection<CourseDTO> listCourse) {
        this.listCourse = listCourse;
    }
    


    /**
     *
     * @return
     */
    public AnswerDTO getAnswerDTO() {
        return answerDTO;
    }

    /**
     *
     * @param answerDTO
     */
    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }        

}
