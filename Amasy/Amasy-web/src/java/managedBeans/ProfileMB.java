package managedBeans;

import DTOs.UserDTO;
import entity.User;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;

/**
 *
 * @author Pingeso
 */
@Named(value = "profileMB")
@RequestScoped
public class ProfileMB {
    @Inject 
    LoginSessionMB varSession;
    
    private UserDTO userDTO;
    
    public ProfileMB() {
    }
    
    @PostConstruct
    public void init() throws ServletException{
        userDTO = varSession.getUser();
    }  
    
    public void viewProfile(){
        UtilitiesMB.redirection("/faces/global/viewProfile.xhtml");
    }
    
    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    
}
