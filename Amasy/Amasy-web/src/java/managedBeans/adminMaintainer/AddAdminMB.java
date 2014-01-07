/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.adminMaintainer;

import DTOs.AnswerDTO;
import DTOs.NewUserDTO;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import managedBeans.UtilitiesMB;
import static managedBeans.teacherMaintainer.AddTeacherMB.validateRut;
import sessionBeans.adminManagement.AdminManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "addAdminMB")
@RequestScoped
public class AddAdminMB {
    @EJB
    private AdminManagementSBLocal adminManagementSB;
    private NewUserDTO newAdmin;

    /**
     * Creates a new instance of AddAdminMB
     */
    public AddAdminMB() {
    }
    
     /**
     *
     */
    @PostConstruct
    public void init() {
        newAdmin = new NewUserDTO();
    }
    
    public void insertNewAdmin(){
        AnswerDTO r = new AnswerDTO();
        newAdmin.setRut(parseRut(newAdmin.getRut()));
        if(validateRut(newAdmin.getRut())){
            if(newAdmin.getRut().contains("K")) newAdmin.setRut(newAdmin.getRut().replace("K",""));
            else newAdmin.setRut(newAdmin.getRut().substring(0, newAdmin.getRut().length()-1));
            r = adminManagementSB.insertNewAdmin(newAdmin);
        }else r.setIdError(110);
        UtilitiesMB.showFeedback(r);
    }
    
    private String parseRut(String rut) {
        rut = rut.toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        return rut;
    }
    
    //http://www.qualityinfosolutions.com/validador-de-rut-chileno-en-java/
    
    /**
     *
     * @param rut
     * @return
     */
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

    public NewUserDTO getNewAdmin() {
        return newAdmin;
    }

    public void setNewAdmin(NewUserDTO newAdmin) {
        this.newAdmin = newAdmin;
    }
    
    
}
