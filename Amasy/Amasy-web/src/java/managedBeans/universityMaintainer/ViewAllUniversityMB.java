/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.universityMaintainer;

import DTOs.AnswerDTO;
import DTOs.ListUniversityDTO;
import DTOs.UniversityDTO;
import java.util.Collection;
import java.util.List;
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
@Named(value = "viewAllUniversityMB")
@RequestScoped
public class ViewAllUniversityMB {
    @EJB
    private UniversityManagementSBLocal universityManagementSB;
    
    @Inject 
    private UniversityMaintainerConversationalMB universityMaintainerConversation;

    private ListUniversityDTO universityList;
    private UniversityDTO selectedUniversity;
    private List<UniversityDTO> filteredUniversities;
    
    public ViewAllUniversityMB() {
    }
    
    @PostConstruct
    void init(){
        getUniversity();
    }
    
    public void getUniversity(){       
        universityList = universityManagementSB.getAllUniversity();
    }
    
    public void editAdmin(Long idUniversity){
        this.universityMaintainerConversation.beginConversation();
        this.universityMaintainerConversation.setIdUniversity(idUniversity);
        UtilitiesMB.redirection("/faces/admin/universityMaintainer/editUniversity.xhtml?cid=".concat(this.universityMaintainerConversation.getConversation().getId().toString()));
    }
    
    public void deleteUniversity(Long idUniversity){        
        AnswerDTO ans = universityManagementSB.deleteUniversity(idUniversity);
        init();
        UtilitiesMB.showFeedback(ans);
   }

    public ListUniversityDTO getUniversityList() {
        return universityList;
    }

    public void setUniversityList(ListUniversityDTO universityList) {
        this.universityList = universityList;
    }

    public UniversityDTO getSelectedUniversity() {
        return selectedUniversity;
    }

    public void setSelectedUniversity(UniversityDTO selectedUniversity) {
        this.selectedUniversity = selectedUniversity;
    }


    public List<UniversityDTO> getFilteredUniversities() {
        return filteredUniversities;
    }

    public void setFilteredUniversities(List<UniversityDTO> filteredUniversities) {
        this.filteredUniversities = filteredUniversities;
    }
    
}
