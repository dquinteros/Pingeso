/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.courseMaintainer;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.LinkedList;
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
    private LinkedList<DateTimeBlockClassCourse> listDateTimeBlockClassCourse;
    /**
     * Creates a new instance of CourseMaintainerConversationalMB
     */
    private Long idCourse;

    public CourseMaintainerConversationalMB() {
    }
    
    public boolean isNotNullListDateTimeBlockClassCourse(){
        if (listDateTimeBlockClassCourse != null) {
            return true;
        } else {
            listDateTimeBlockClassCourse = new LinkedList<>();
            return false;
        }
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

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public LinkedList<DateTimeBlockClassCourse> getListDateTimeBlockClassCourse() {
        if (listDateTimeBlockClassCourse != null) {
            return listDateTimeBlockClassCourse;
        } else {
            listDateTimeBlockClassCourse = new LinkedList<>();
            return new LinkedList<>();
        }
    }        

    public void setListDateTimeBlockClassCourse(LinkedList<DateTimeBlockClassCourse> listDateTimeBlockClassCourse) {
        this.listDateTimeBlockClassCourse = listDateTimeBlockClassCourse;
    }
}
