package managedBeans.universityMaintainer;

import DTOs.AnswerDTO;
import DTOs.UniversityDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import managedBeans.UtilitiesMB;
import sessionBeans.universityManagement.UniversityManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "addUniversityMB")
@RequestScoped
public class AddUniversityMB {
    @EJB
    private UniversityManagementSBLocal universityManagementSB;

    private UniversityDTO newUniversity;
    private String rutTemp;
    
    public AddUniversityMB() {
    }
    
    @PostConstruct
    public void init(){
        newUniversity = new UniversityDTO();
    }
    
    public void insertNewUniversity(){
        AnswerDTO ans = new AnswerDTO();
        rutTemp = parseRut(rutTemp);
        if(validateRut(rutTemp)){
            if(rutTemp.contains("K")) rutTemp = rutTemp.replace("K","");
            else rutTemp = rutTemp.substring(0, rutTemp.length()-1);
            ans = universityManagementSB.insertNewUniversity(newUniversity,rutTemp);
        }else ans.setIdError(110);
        if(ans.getIdError()==0){
            newUniversity.setAddress("");
            newUniversity.setName("");
            rutTemp = "";
        }
        UtilitiesMB.showFeedback(ans);
    }

    private String parseRut(String rut){
        rut = rut.toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        return rut;
    }
    
    public static boolean validateRut(String rut) {
        boolean validacion = false;
        try {
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }
    
    public UniversityDTO getNewUniversity() {
        return newUniversity;
    }

    public void setNewUniversity(UniversityDTO newUniversity) {
        this.newUniversity = newUniversity;
    }

    public String getRutTemp() {
        return rutTemp;
    }

    public void setRutTemp(String rutTemp) {
        this.rutTemp = rutTemp;
    }
    
}
