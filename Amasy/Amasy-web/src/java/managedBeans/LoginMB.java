/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;

/**
 *
 * @author Pingeso
 */
@Named(value = "loginMB")
@RequestScoped
public class LoginMB {
    @Inject loginSessionMB varSession;
    /**
     * Creates a new instance of LoginMB
     */
    private String userName;
    private String password;
    
    public LoginMB() {
    }
    
    @PostConstruct
    public void init() throws ServletException {
        varSession.loginVerification();        
    }   

    public void login(){
        varSession.login(userName, password);
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
