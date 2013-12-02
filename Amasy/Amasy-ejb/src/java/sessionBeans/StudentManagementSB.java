/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DTOs.UserDTO;
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
        UserDTO studentDTOTemp;
        Query q = this.em.createNamedQuery("Student.getAllStudentUserInfo");
        try {
            result = (Collection<User>)q.getResultList();
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
        catch (NoResultException nre) {
            System.out.println(nre);
            return null;
        }                                       
    }
    

}
