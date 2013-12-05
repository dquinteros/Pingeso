/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.studentManagement;

import DTOs.UserDTO;
import entity.Student;
import entity.User;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
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
public class StudentManagementSB implements StudentManagementSBLocal {

    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    @SuppressWarnings("empty-statement")
    public LinkedList<UserDTO> getAllStudent() {
        Collection<User> result;
        LinkedList<UserDTO> exitResult = new LinkedList<UserDTO>();
        Query q = this.em.createNamedQuery("Student.getAllStudentUserInfo");
        try {
            result = (Collection<User>) q.getResultList();
            return sqlResultToUserList(result, exitResult);
        } catch (NoResultException nre) {
            System.out.println(nre);
            return null;
        }
    }

    private LinkedList<UserDTO> sqlResultToUserList(Collection<User> result, LinkedList<UserDTO> exitResult) {
        UserDTO studentDTOTemp;
        for (User iter : result) {
            studentDTOTemp = new UserDTO();
            studentDTOTemp.setCellPhone(iter.getCellPhone());
            studentDTOTemp.setEmail(iter.getEmail());
            studentDTOTemp.setFirstName(iter.getFirstName());
            studentDTOTemp.setHomePhone(iter.getHomePhone());
            studentDTOTemp.setLastName(iter.getLastName());
            studentDTOTemp.setRut(iter.getRut());
            studentDTOTemp.setUserName(iter.getUserName());
            exitResult.add(studentDTOTemp);
        }
        return exitResult;
    }

    @Override
    public boolean insertNewStudent(User user, Date incomeYear) {

        if (user == null) {
            return false;
        }
        User u = null;
        try{
        Query q = this.em.createNamedQuery("User.findByRut", User.class);
         q.setParameter("rut", user.getRut());
         u = (User) q.getSingleResult();
        }catch(NullPointerException e){
         e.printStackTrace();
        }       
        if (u != null) {
            return false;
        }

        Student newStudent = new Student();

        newStudent.setUser(user);
        newStudent.setIncomeYear(incomeYear);

        getEm().getTransaction().begin();
        persist(newStudent);
        getEm().getTransaction().commit();
        getEm().close();

        return true;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    
}