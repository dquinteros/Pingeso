/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 *
 * @author Pingeso
 */
@Named(value = "courseMaintainerConversationalMB")
@ConversationScoped
public class CourseMaintainerConversationalMB implements Serializable {
    @Inject
    Conversation conversation;
    /**
     * Creates a new instance of CourseMaintainerConversationalMB
     */
    
    private String courseName;
    
    public CourseMaintainerConversationalMB() {
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    
}
