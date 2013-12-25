/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.adminManagement;

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
public class AdminManagementSB implements AdminManagementSBLocal {
    
    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    @SuppressWarnings("empty-statement")    
    public LinkedList<UserDTO> getAllAdmin() {
        System.out.println("(AMSB/getAllAdmin) A buscar todos los admin!");
        Collection<User> result;
        LinkedList<UserDTO> exitResult = new LinkedList<UserDTO>();
        System.out.println("(AMSB/getAllAdmin) Mandando Query...");
        Query q = this.em.createNamedQuery("Admin.getAllAdminUserInfo");                
        try {
            System.out.println("(AMSB/getAllAdmin) Obteniendo lista de usuarios...");
            result = (Collection<User>) q.getResultList();
            System.out.println("(AMSB/getAllAdmin) Devolviendo lista de usuarios...");
            return sqlResultToUserList(result, exitResult);
        } catch (NoResultException nre) {
            System.out.println("error: "+nre);
            return null;
        }
    }
    
    private LinkedList<UserDTO> sqlResultToUserList(Collection<User> result, LinkedList<UserDTO> exitResult) {
        UserDTO studentDTOTemp;
        for (User iter : result) {
            studentDTOTemp = new UserDTO(iter);            
            exitResult.add(studentDTOTemp);
        }
        return exitResult;
    }


}
