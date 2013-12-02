/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DTOs.UserDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import sessionBeans.UserManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "loginSessionMB")
@SessionScoped
public class LoginSessionMB extends UtilitiesMB implements Serializable{        

    /**
     * Creates a new instance of LoginSessionMB
     */
    private UserDTO user;
    private String startPage;
    
    @EJB
    private UserManagementSBLocal userManagementSB;
    
   
    public void loginVerification() throws ServletException {
        System.out.println("init: "+startPage);
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        if (request.getRemoteUser() != null) {
            redirectStartPage();
        }
    }        
    
    public LoginSessionMB() {
    }
    
    public void login(String userName, String pass){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            if (request.getRemoteUser() == null) {
                System.out.println("entro: "+ request.toString());
                request.login(userName, pass);  
                System.out.println("entro2");
                user = userManagementSB.findUserByUserName(userName);
                defineStartPage();
                redirectStartPage();
            }
            else {
                redirectStartPage();
            }
        }
        catch (Exception e) {
            System.out.println("error(loginMB-login): "+e.getMessage());
        }
    }
    
    private void defineStartPage(){      
        switch(user.getUserType().getName()){
            case "Profesor": startPage = "/faces/teacher/index.xhtml";
                break;
            case "Administrador":;
                break;
            case "Alumno":;
                break;
        }
    }
    
    private void redirectStartPage(){
        LoginSessionMB.redirection(startPage);
    }
   
    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }
    
}
