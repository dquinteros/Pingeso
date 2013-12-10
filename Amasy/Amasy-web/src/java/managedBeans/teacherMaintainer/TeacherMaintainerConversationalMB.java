package managedBeans.teacherMaintainer;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

@Named(value = "teacherMaintainerConversationalMB")
@ConversationScoped
public class TeacherMaintainerConversationalMB implements Serializable {
    
    @Inject
    Conversation conversation;
    
    private Long idUser;
    
    public TeacherMaintainerConversationalMB() {
    }
    
    public void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

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
