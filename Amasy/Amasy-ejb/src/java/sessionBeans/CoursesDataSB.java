/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.BlockClass;
import entity.Course;
import entity.Teacher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pingeso
 */
@Stateless
public class CoursesDataSB implements CoursesDataSBLocal {
    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;

    /**
     *
     * @param object
     */
    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    /**
     *
     * @param profesorActual
     * @return
     */
    @Override
    public ArrayList<Course> teacherCourses(long profesorActual) {        
        Collection<Course> res = em.find(Teacher.class, profesorActual).getListCourse();        
        ArrayList<Course> listaCursos = new ArrayList<> ();     
        for (Course iter : res) { 
            if(getIdBlockClassForTakeAttendance(iter.getId())!=null){
                listaCursos.add(iter);                          
            }                                 
        }
        return listaCursos;
    }
    
    private BlockClass getIdBlockClassForTakeAttendance(Long course) {      
        Collection<BlockClass> res = em.find(Course.class, course).getListBlockClass(); 
        if(res.isEmpty()){
            return null;
        }
        for (BlockClass iter : res) {
            if( validAssistanceTimebox(course, iter)){
                return iter;
            }
        }
        return null;
    } 
    
    private boolean validAssistanceTimebox(Long idCourse, BlockClass blockclass){
        Course course = em.find(Course.class, idCourse);
        Date date = new Date();
        if(blockclass.getDate().getTime()/1000 -course.getMinutesBeforeClassStart()*60 <= date.getTime()/1000 && date.getTime()/1000 <= blockclass.getDate().getTime()/1000+course.getMinutesAfterClassStart()*60){
            return true;
        }else{
            return false;
        }
        
    }

}
