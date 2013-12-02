/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.takeAttendance;

import DTOs.ResponseAssistanceDTO;
import entity.BlockClass;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import sessionBeans.TakeAttendanceSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "takeAttendanceConversationMB")
@ConversationScoped
public class TakeAttendanceConversationMB implements Serializable {

    @EJB
    private TakeAttendanceSBLocal TakeAttendanceSB;
    @Inject
    Conversation conversation;
    private long idClass;
    private BlockClass blockClass;

    public TakeAttendanceConversationMB() {
    }

    @PostConstruct
    public void init() {
        this.idClass = -1;
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

    public BlockClass getBlockClass() {
        return blockClass;
    }

    public void setBlockClass(BlockClass blockClass) {
        this.blockClass = blockClass;
    }
    
    
}
