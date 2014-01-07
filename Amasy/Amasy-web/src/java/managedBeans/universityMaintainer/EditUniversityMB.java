package managedBeans.universityMaintainer;

import DTOs.AnswerDTO;
import DTOs.UniversityDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import managedBeans.UtilitiesMB;
import sessionBeans.universityManagement.UniversityManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "editUniversityMB")
@RequestScoped
public class EditUniversityMB {
    @EJB
    private UniversityManagementSBLocal universityManagementSB;
    @Inject
    private UniversityMaintainerConversationalMB universityMaintainerConversation;
    
    private UniversityDTO newUniversity;
    private Long universityId;
    
    public EditUniversityMB() {
    }
    
    @PostConstruct
    public void init() {
        universityId = universityMaintainerConversation.getIdUniversity();
        newUniversity = universityManagementSB.getUniversityById(universityId);
    }
    
    public void editCurrentUniversity(){
        AnswerDTO ans = new AnswerDTO();
        ans = universityManagementSB.updateUniversity(newUniversity, universityId);
        UtilitiesMB.showFeedback(ans);
    }

    public UniversityDTO getNewUniversity() {
        return newUniversity;
    }

    public void setNewUniversity(UniversityDTO newUniversity) {
        this.newUniversity = newUniversity;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }
    
}
