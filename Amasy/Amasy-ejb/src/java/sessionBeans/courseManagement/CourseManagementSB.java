/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.courseManagement;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import entity.Course;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import sessionBeans.TakeAttendanceSB;

/**
 *
 * @author Pingeso
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CourseManagementSB implements CourseManagementSBLocal {
    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;

    @Resource
    UserTransaction ut;
    
     /**
     *
     * @param object
     * @return
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean persistInsert(Object object) {
        try {
            ut.begin(); // Start a new transaction
            try {
                em.persist(object);
                ut.commit(); // Commit the transaction
                return true;
            } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException e) {
                ut.rollback(); // Rollback the transaction
                return false;
            }
        } catch (NotSupportedException | SystemException ex) {
            Logger.getLogger(TakeAttendanceSB.class.getName()).log(Level.SEVERE, null, ex); // Rollback the transaction
            return false;
        }
    }
    
    /**
     *
     * @return
     */
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

    /**
     *
     * @param object
     */
    public void persist(Object object) {
        em.persist(object);
    }
    
    /**
     *
     * @param courseDTO
     * @return
     */
    @Override
    public AnswerDTO insertNewCourse(CourseDTO courseDTO) {
        System.out.println("(CourseManagementSB) Insertando nuevo curso...");
        AnswerDTO existCourseName = validateCourseRegistry(courseDTO);
        if(existCourseName.getIdError()!=0){
            return existCourseName;
        }
        Course course = newCourse(courseDTO);
        System.out.println("(CourseManagementSB) Ahora, llamando a persistencia...");
        persistInsert(course);
        return new AnswerDTO(0);
    }

    private AnswerDTO validateCourseRegistry(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return new AnswerDTO(109);
        }
        boolean existName = existName(courseDTO.getName());
         if (existName) {
            return new AnswerDTO(125);
        } else{
            return new AnswerDTO(000);
        }
    }

    private Course newCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        System.out.println("(CourseManagementSB) course.setLevel(courseDTO.getLevel())");
        course.setLevel(courseDTO.getLevel());
        return course;
    }

   

    private boolean existName(String name) {
        Long count;
        Query q = em.createNamedQuery("Course.countCourseByName", Course.class);
        q.setParameter("name", name);
        count = (Long) q.getSingleResult();
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }
}
