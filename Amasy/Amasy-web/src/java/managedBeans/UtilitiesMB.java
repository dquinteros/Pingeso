package managedBeans;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import DTOs.AnswerDTO;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pingeso
 */
@Named(value = "utilitiesMB")
@RequestScoped
public class UtilitiesMB {

    /**
     * Creates a new instance of UtilitiesMB
     */
    public UtilitiesMB() {
    }
    
    public static void redirection(String url){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
           externalContext.redirect(externalContext.getRequestContextPath() + url);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static String getResponseServer(AnswerDTO r){
        switch(r.getIdError()){
            case 0: return   "Operación realizada con éxito";
            case 101: return "Nombre de usuario, email y rut ya registrados";
            case 102: return "Email y nombre de usuario ya registrados";
            case 103: return "Nombre de usuario y rut ya registrados";
            case 104: return "Email y rut ya registrados";
            case 105: return "Email ya registrado";
            case 106: return "Nombre de usuario ya registrado";
            case 107: return "Rut ya registrado";
            case 108: return "Usuario o contraseña no válido";
            case 109: return "Error con el registro de estudiante";
            case 110: return "Rut no válido";
            default: return  "Error";
        }        
    }
    
    private static String getTypeMessage(AnswerDTO r){
        switch(r.getIdError()){
            case 0: return   "success";
            case 101: return "error";
            case 102: return "error";
            case 103: return "error";
            case 104: return "error";
            case 105: return "error";
            case 106: return "error";
            case 107: return "error";
            case 108: return "error";
            case 109: return "error";
            case 110: return "error";
            default: return  "error";
        } 
    }
    
    public static void showFeedback(AnswerDTO answer){
        String message = getResponseServer(answer);
        String typeMessage = getTypeMessage(answer);
        FacesContext context = FacesContext.getCurrentInstance();
        switch(typeMessage){
            case "success": context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", message)); break;
            case "error": context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"", message)); break;
            default: context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"", "Error"));
        }
    }
    
    
}
