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

    /**
     *
     * @param course
     * @param blockClass
     * @return
     */
    public ArrayList<UserAssistantBlockClassDTO> listOfStudentsPerCourseList(long course, long blockClass);

    /**
     *
     * @param course
     * @return
     */
    public BlockClass getIdBloackClassForTakeAttendance(Long course);
    
    /**
     *
     * @param fingerprint
     * @param blockClass
     * @return
     */
    public ResponseAssistanceDTO validateFingerprintBM(String fingerprint, BlockClass blockClass);
    
}
