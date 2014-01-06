/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.universityMaintainer;

import DTOs.ListUniversityDTO;
import DTOs.UniversityDTO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
