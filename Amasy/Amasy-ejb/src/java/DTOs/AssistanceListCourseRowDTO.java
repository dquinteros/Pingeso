/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Pingeso
 */
@Stateless
@LocalBean
public class AssistanceListCourseRowDTO {

    private List<AssistanceListCourseUnitDTO> listUnit;

    public List<AssistanceListCourseUnitDTO> getListUnit() {
        return listUnit;
    }

    public void setListUnit(List<AssistanceListCourseUnitDTO> listUnit) {
        this.listUnit = listUnit;
    }
}
