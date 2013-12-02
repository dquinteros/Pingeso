/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DTOs.ResponseAssistanceDTO;
import DTOs.UserAssistantBlockClassDTO;
import entity.BlockClass;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author Pingeso
 */
@Local
public interface TakeAttendanceSBLocal {

    public ArrayList<UserAssistantBlockClassDTO> listOfStudentsPerCourseList(long course, long blockClass);

    public BlockClass getIdBloackClassForTakeAttendance(Long course);
    
    public ResponseAssistanceDTO validateFingerprintBM(String fingerprint, BlockClass blockClass);
    
}
