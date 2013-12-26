/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.adminMaintainer;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 *
 * @author Pingeso
 */
@Named(value = "adminMaintainerConversationalMB")
@ConversationScoped
public class AdminMaintainerConversationalMB implements Serializable {
    
    @Inject
    Conversation conversation;
    
    private Long idUser;

    /**
     * Creates a new instance of AdminMaintainerConversationalMB
     */
    public AdminMaintainerConversationalMB() {
    }
    
    /**
     *
     */
    public void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    /**
     *
     */
    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public Conversation getConversation() {
        return conversation;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    
    
}
