/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DTOs.AnswerDTO;
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
    
   
    /**
     *
     * @throws ServletException
     */
    public void loginVerification() throws ServletException {
        System.out.println("init: "+startPage);
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        if (request.getRemoteUser() != null) {
            redirectStartPage();
        }
    }        
    
    /**
     *
     */
    public LoginSessionMB() {
    }
    
    /**
     *
     * @param userName
     * @param pass
     * @return
     */
    public AnswerDTO login(String userName, String pass){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            if (request.getRemoteUser() == null) {//no esta conectado
                request.login(userName, pass);  
                user = userManagementSB.findUserByUserName(userName);
                if(user==null){
                    logout();
                    return new AnswerDTO(122);
                }
                defineStartPage();
                redirectStartPage();
                return new AnswerDTO(0);
            }
            else {   
                if(user==null){
                    logout();
                    return new AnswerDTO(122);
                }
                redirectStartPage();
                return new AnswerDTO(0);
            }
        }
        catch (Exception e) {            
            System.out.println("error(loginMB-login): "+e.getMessage());
            return new AnswerDTO(123);
        }
    }
    
    /**
     *
     */
    public void logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        startPage = null;
        user = null;
        UtilitiesMB.redirection("/faces/login.xhtml");
    }
    
    private void defineStartPage(){      
        switch(user.getUserType()){
            case "Profesor": startPage = "/faces/teacher/index.xhtml";
                break;
            case "Administrador": startPage = "/faces/admin/index.xhtml";
                break;
            case "Alumno":;
                break;
        }
    }
    
    private void redirectStartPage(){
        LoginSessionMB.redirection(startPage);
    }
   
    /**
     *
     * @return
     */
    public String getStartPage() {
        return startPage;
    }

    /**
     *
     * @param startPage
     */
    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    /**
     *
     * @return
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(UserDTO user) {
        this.user = user;
    }
    
    
    
}
