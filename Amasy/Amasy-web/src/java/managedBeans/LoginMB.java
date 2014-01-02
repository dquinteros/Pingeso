/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import DTOs.AnswerDTO;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import java.util.logging.Logger; 

/**
 *
 * @author Pingeso
 */
@Named(value = "loginMB")
@RequestScoped
public class LoginMB {
    @Inject LoginSessionMB varSession;
    /**
     * Creates a new instance of LoginMB
     */
    private String userName;
    private String password;
    private Logger log = Logger.getLogger(LoginMB.class.getName()); 
    
    /**
     *
     */
    public LoginMB() {
    }
    
    /**
     *
     * @throws ServletException
     */
    @PostConstruct
    public void init() throws ServletException {
        varSession.loginVerification();        
    }   

    /**
     *
     */
    public void login(){
        AnswerDTO res = varSession.login(userName, password);
        UtilitiesMB.showFeedback(res);
        log.info("El usuario "+userName+"("+varSession.getUser().getRut()+")"+" ingresó en el sistema" ); 
    }
   
    /**
     *
     */
    public void logout(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();        
        varSession.setStartPage("");
        varSession.setUser(null);        
    }
    
    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
   
    
}
