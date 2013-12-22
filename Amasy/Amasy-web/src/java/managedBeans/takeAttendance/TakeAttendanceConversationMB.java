/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.takeAttendance;

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

    /**
     *
     */
    public TakeAttendanceConversationMB() {
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        this.idClass = -1;
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
    public long getIdClass() {
        return idClass;
    }

    /**
     *
     * @param idClass
     */
    public void setIdClass(long idClass) {
        this.idClass = idClass;
    }

    /**
     *
     * @return
     */
    public BlockClass getBlockClass() {
        return blockClass;
    }

    /**
     *
     * @param blockClass
     */
    public void setBlockClass(BlockClass blockClass) {
        this.blockClass = blockClass;
    }
    
    
}
