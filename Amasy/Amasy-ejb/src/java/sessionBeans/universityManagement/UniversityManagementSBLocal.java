/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.universityManagement;

import DTOs.AnswerDTO;
import DTOs.ListUniversityDTO;
import DTOs.UniversityDTO;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface UniversityManagementSBLocal {
    
    ListUniversityDTO getAllUniversity();
    
    AnswerDTO insertNewUniversity(UniversityDTO university, String rutTemp);
    
    UniversityDTO getUniversityById(Long universityId);
    
    AnswerDTO updateUniversity(UniversityDTO newUniversity, Long universityId);
    
    AnswerDTO deleteUniversity(Long id);
    
}
