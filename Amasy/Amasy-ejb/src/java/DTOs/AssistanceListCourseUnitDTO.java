/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class AssistanceListCourseUnitDTO {

    private String text;
    private boolean present;
    private boolean textAssistance;
    private boolean textRutName;
    private Long idBlockClass;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public boolean isTextAssistance() {
        return textAssistance;
    }

    public void setTextAssistance(boolean textAssistance) {
        this.textAssistance = textAssistance;
    }

    public boolean isTextRutName() {
        return textRutName;
    }

    public void setTextRutName(boolean textRutName) {
        this.textRutName = textRutName;
    }

    public Long getIdBlockClass() {
        return idBlockClass;
    }

    public void setIdBlockClass(Long idBlockClass) {
        this.idBlockClass = idBlockClass;
    }

   
}
