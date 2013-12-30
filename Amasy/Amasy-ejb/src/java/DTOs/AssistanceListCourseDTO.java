/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Pingeso
 */
@Stateless
public class AssistanceListCourseDTO {

    private List<AssistanceListCourseTitleDTO> listTitle;
    private List<AssistanceListCourseRowDTO> listRow;

    public List<AssistanceListCourseTitleDTO> getListTitle() {
        return listTitle;
    }

    public void setListTitle(List<AssistanceListCourseTitleDTO> listTitle) {
        this.listTitle = listTitle;
    }

    public List<AssistanceListCourseRowDTO> getListRow() {
        return listRow;
    }

    public void setListRow(List<AssistanceListCourseRowDTO> listRow) {
        this.listRow = listRow;
    }
}
