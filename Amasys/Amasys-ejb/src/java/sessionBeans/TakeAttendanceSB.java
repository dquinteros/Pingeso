/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entity.Course;
import entity.Student;
import entity.User;
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
public class TakeAttendanceSB implements TakeAttendanceSBLocal {
    @PersistenceContext(unitName = "Amasys-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public ArrayList<User> listOfStudentsPerCourseList(long course) {        
        Collection<Student> res = em.find(Course.class, course).getListStudent();                        
        /*Query q = this.em.createNamedQuery("Course.getAllStudentsOfCourse");
        //q.setParameter("idUsuario", idUsuario);
        Collection<Student> res = (Collection<Student>)q.getResultList();       */        
        ArrayList<User> listaUsuarios = new ArrayList<> ();        
        User userTemp;
        for (Student iter : res) {            
            userTemp = em.find(User.class, iter.getId());
            
            listaUsuarios.add(userTemp);                        
                       
        }
        return listaUsuarios;
    }

}
