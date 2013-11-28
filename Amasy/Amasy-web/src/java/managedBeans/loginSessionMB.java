/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
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
public class loginSessionMB extends utilitiesMB implements Serializable{        

    /**
     * Creates a new instance of loginSessionMB
     */
    private String userName;
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
    
    public loginSessionMB() {
    }
    
    public void login(String userName, String pass){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            if (request.getRemoteUser() == null) {
                request.login(userName, pass);  
                this.userName = userName;
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
        String tipeUser = userManagementSB.findUserTypeByUserName(userName);
        System.out.println(tipeUser);        
        switch(tipeUser){
            case "Profesor": startPage = "/faces/teacher/index.xhtml";
                break;
            case "Administrador":;
                break;
            case "Alumno":;
                break;
        }
    }
    
    private void redirectStartPage(){
        loginSessionMB.redirection(startPage);
    }
   
    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
