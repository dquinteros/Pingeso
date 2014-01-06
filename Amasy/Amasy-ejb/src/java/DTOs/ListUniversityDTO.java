/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class ListUniversityDTO {

    private Collection<UniversityDTO> listUniversity;
    private AnswerDTO answerDTO;

    public ListUniversityDTO() {
    }

    public ListUniversityDTO(Collection<UniversityDTO> listUniversity, AnswerDTO answerDTO) {
        this.listUniversity = listUniversity;
        this.answerDTO = answerDTO;
    }

    public Collection<UniversityDTO> getListUniversity() {
        return listUniversity;
    }

    public void setListUniversity(Collection<UniversityDTO> listUniversity) {
        this.listUniversity = listUniversity;
    }

    public AnswerDTO getAnswerDTO() {
        return answerDTO;
    }

    public void setAnswerDTO(AnswerDTO answerDTO) {
        this.answerDTO = answerDTO;
    }
    
}
