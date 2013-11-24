/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DTOs.TakeAttendanceDataUserDTO;
import entity.BlockClass;
import entity.Course;
import entity.Student;
import entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pingeso
 */
@Stateless
public class TakeAttendanceSB implements TakeAttendanceSBLocal {
    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public ArrayList<TakeAttendanceDataUserDTO> listOfStudentsPerCourseList(long course, long blockClass) {          
        Collection<Student> res = em.find(Course.class, course).getListStudent();
        ArrayList<TakeAttendanceDataUserDTO> listTakeAttendanceDataUser = new ArrayList<> ();
        Long resultado;
        TakeAttendanceDataUserDTO takeAttendanceDataUserTemp;
        System.out.println("alumnosPorCurso = "+res.size());
        User userTemp;
        for(Student iter : res){            
            
            userTemp = ((Student)em.find(Student.class, iter.getId())).getUser();
            System.out.println("userTemp = "+userTemp.getFirstName());
                    
            System.out.println("iter.getId() = "+iter.getId());
            Query q = this.em.createNamedQuery("Assistance.findStudentAssistance");
            q.setParameter("idStudent", iter.getId());
            q.setParameter("idBlockClass", blockClass);
            //System.out.println("userTemp.id = "+ userTemp.toString());
            try {
                resultado = (Long)q.getSingleResult();
                System.out.println("(Long)q.getSingleResult() = "+(Long)q.getSingleResult());
                if(resultado==1){
                    takeAttendanceDataUserTemp = new TakeAttendanceDataUserDTO();
                    takeAttendanceDataUserTemp.TakeAttendanceDataUser(iter.getUser(), true);
                    listTakeAttendanceDataUser.add(takeAttendanceDataUserTemp);
                }else{
                    takeAttendanceDataUserTemp = new TakeAttendanceDataUserDTO();
                    takeAttendanceDataUserTemp.TakeAttendanceDataUser(iter.getUser(), false);
                    listTakeAttendanceDataUser.add(takeAttendanceDataUserTemp);
                }
            }
            catch (NoResultException nre) {
                System.out.println(nre);
                return null;
            }                       
        }
        return listTakeAttendanceDataUser;
    }

    
    
    @Override
    public BlockClass getIdBloackClassForTakeAttendance(Long course) {
        Date date = new Date();        
        Collection<BlockClass> res = em.find(Course.class, course).getListBlockClass(); 
        if(res.isEmpty()){
            return null;
        }
        System.out.println(date);
        for (BlockClass iter : res) {
            if( iter.getDate().getTime()/1000 -5*60 <= date.getTime()/1000 && date.getTime()/1000 <= iter.getDate().getTime()/1000+30*60){
                return iter;
            }
        }
        return null;
    }           
}
