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

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public ArrayList<Course> teacherCourses(long profesorActual) {        
        Collection<Course> res = em.find(Teacher.class, profesorActual).getListCourse();        
        ArrayList<Course> listaCursos = new ArrayList<> ();     
        for (Course iter : res) { 
            if(getIdBloackClassForTakeAttendance(iter.getId())!=null){
                listaCursos.add(iter);                          
            }                                 
        }
        return listaCursos;
    }
    
    private BlockClass getIdBloackClassForTakeAttendance(Long course) {
        Date date = new Date();        
        Collection<BlockClass> res = em.find(Course.class, course).getListBlockClass(); 
        if(res.isEmpty()){
            return null;
        }
        for (BlockClass iter : res) {
            if( iter.getDate().getTime()/1000 -5*60 <= date.getTime()/1000 && date.getTime()/1000 <= iter.getDate().getTime()/1000+30*60){
                return iter;
            }
        }
        return null;
    } 

}
