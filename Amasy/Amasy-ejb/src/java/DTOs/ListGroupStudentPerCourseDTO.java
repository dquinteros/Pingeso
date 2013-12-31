/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entity.GroupStudentPerCourse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class ListGroupStudentPerCourseDTO {

    private List<GroupStudentPerCourseDTO> listGroup;
    private AnswerDTO answerDTO;

    public ListGroupStudentPerCourseDTO() {
    }

    public ListGroupStudentPerCourseDTO(Collection<GroupStudentPerCourse> listGroup, AnswerDTO answerDTO) {
        this.listGroup = new ArrayList<GroupStudentPerCourseDTO>();
        GroupStudentPerCourseDTO groupStudentPerCourseDTO;
        for (GroupStudentPerCourse it : listGroup) {
            groupStudentPerCourseDTO = new GroupStudentPerCourseDTO();
            groupStudentPerCourseDTO.setId(it.getId());
            groupStudentPerCourseDTO.setName(it.getName());
            this.listGroup.add(groupStudentPerCourseDTO);
        }
        this.answerDTO = answerDTO;
    }

    public List<GroupStudentPerCourseDTO> getListGroup() {
        return listGroup;
    }

    public void setListGroup(List<GroupStudentPerCourseDTO> listGroup) {
        this.listGroup = listGroup;
    }

    public AnswerDTO getAnswerDTO() {
        return answerDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }
}
