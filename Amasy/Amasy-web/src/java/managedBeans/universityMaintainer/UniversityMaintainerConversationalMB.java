package managedBeans.universityMaintainer;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 *
 * @author Pingeso
 */
@Named(value = "universityMaintainerConversationalMB")
@ConversationScoped
public class UniversityMaintainerConversationalMB implements Serializable {

    @Inject
    Conversation conversation;
    
    private Long idUniversity;
    
    public UniversityMaintainerConversationalMB() {
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

    public Long getIdUniversity() {
        return idUniversity;
    }

    public void setIdUniversity(Long idUniversity) {
        this.idUniversity = idUniversity;
    }
    
}
