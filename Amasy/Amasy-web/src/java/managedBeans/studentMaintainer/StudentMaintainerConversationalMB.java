/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.studentMaintainer;

import DTOs.AnswerDTO;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 *
 * @author Pingeso
 */
@Named(value = "studentMaintainerConversationalMB")
@ConversationScoped
public class StudentMaintainerConversationalMB implements Serializable {
    @Inject
    Conversation conversation;
    
    private Long idUser;
    
    /**
     *
     */
    public StudentMaintainerConversationalMB() {
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

    /**
     *
     * @return
     */
    public Conversation getConversation() {
        return conversation;
    }

    /**
     *
     * @return
     */
    public Long getIdUser() {
        return idUser;
    }

    /**
     *
     * @param idUser
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    
}
