/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans.takeAttendance;

import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Pingeso
 */
@Named(value = "takeAttendanceConversationMB")
@RequestScoped
public class takeAttendanceConversationMB implements Serializable {
    @Inject 
    Conversation conversation;
    
    private long idClass;
    
    public takeAttendanceConversationMB() {  
    }
        
    @PostConstruct
    public void init() {
        //this.idClass = -1;
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
    
    public long getIdClass() {
        return idClass;
    }

    public void setIdClass(long idClass) {
        this.idClass = idClass;
    }
    
}
