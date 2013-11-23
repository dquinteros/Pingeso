/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Course;
import entity.Teacher;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pingeso
 */
@Stateless
public class CoursesDataSB implements CoursesDataSBLocal {
    @PersistenceContext(unitName = "Amasys-ejbPU")
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
            listaCursos.add(iter);                                               
        }
        return listaCursos;
    }

}
