/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.courseManagement;

import DTOs.CourseDTO;
import DTOs.UserDTO;
import entity.Course;
import entity.User;
import java.util.Collection;
import java.util.LinkedList;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author Pingeso
 */
@Stateless
public class CourseManagementSB implements CourseManagementSBLocal {
    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;

    @Resource
    UserTransaction ut;
    
    @Override
    @SuppressWarnings("empty-statement")
    public LinkedList<CourseDTO> getAllCourse() {
        Collection<Course> result;
        LinkedList<CourseDTO> exitResult = new LinkedList<CourseDTO>();
        Query q = this.em.createNamedQuery("Course.getAllCourses");
        try {
            result = (Collection<Course>) q.getResultList();
            return sqlResultToCourseList(result, exitResult);
        } catch (NoResultException nre) {
            System.out.println(nre);
            return null;
        }
    }

    private LinkedList<CourseDTO> sqlResultToCourseList(Collection<Course> result, LinkedList<CourseDTO> exitResult) {
        CourseDTO courseDTOTemp;
        for (Course iter : result) {
            courseDTOTemp = new CourseDTO(iter);            
            exitResult.add(courseDTOTemp);
        }
        return exitResult;
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
