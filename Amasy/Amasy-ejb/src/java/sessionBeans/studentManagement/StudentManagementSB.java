/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.studentManagement;

import DTOs.UserDTO;
import entity.Student;
import entity.User;
import java.util.Collection;
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
    public boolean insertNewStudent(User user) {

        User u = em.find(User.class, user.getRut());
        if (u != null) {
            return false;
        }

        Student newStudent = new Student();

        newStudent.setUser(user);
        newStudent.setId(null);
        newStudent.setListCourse(null);
        newStudent.setIncomeYear(null);

        em.getTransaction().begin();
        persist(newStudent);
        em.getTransaction().commit();
        em.close();

        return false;
    }
}