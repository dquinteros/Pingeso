package managedBeans.teacherMaintainer;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 *
 * @author Pingeso
 */
@Named(value = "teacherMaintainerConversationalMB")
@ConversationScoped
public class TeacherMaintainerConversationalMB implements Serializable {
    
    @Inject
    Conversation conversation;
    
    private Long idUser;
    
    /**
     *
     */
    public TeacherMaintainerConversationalMB() {
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
