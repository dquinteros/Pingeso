package managedBeans;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
}
