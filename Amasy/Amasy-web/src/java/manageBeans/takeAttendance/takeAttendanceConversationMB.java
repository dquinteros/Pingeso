/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manageBeans.takeAttendance;

import entity.BlockClass;
import entity.User;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import sessionBeans.fingerPrint.FingerprintManagementSBLocal;

/**
 *
 * @author Pingeso
 */
@Named(value = "takeAttendanceConversationMB")
@ConversationScoped
public class takeAttendanceConversationMB implements Serializable {

    @EJB
    private FingerprintManagementSBLocal fingerprintManagementSB;
    @Inject
    Conversation conversation;
    private long idClass;
    private String fingerprint;
    private BlockClass blockClass;

    public takeAttendanceConversationMB() {
    }

    @PostConstruct
    public void init() {
        this.idClass = -1;
    }

    public void sendFingerprint() {
        User nada = fingerprintManagementSB.validateFingerprintBM(fingerprint, blockClass);
       // this.redirection("/faces/teacher/takeAttendance/takeAttendance.xhtml?cid=".concat(this.takeAttendanceConversation.getConversation().getId().toString()));               
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

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public BlockClass getBlockClass() {
        return blockClass;
    }

    public void setBlockClass(BlockClass blockClass) {
        this.blockClass = blockClass;
    }
    
    
}
