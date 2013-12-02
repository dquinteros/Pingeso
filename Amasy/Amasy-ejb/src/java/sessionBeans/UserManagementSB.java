/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DTOs.UserDTO;
import entity.User;
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
public class UserManagementSB implements UserManagementSBLocal {
    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public UserDTO findUserByRut(int rut) {
        Query q = this.em.createNamedQuery("User.findByRut", User.class);
        q.setParameter("rut", rut);
        UserDTO res = null;
        try {
            res = new UserDTO((User) q.getSingleResult());
        } catch (NoResultException e) {
            System.out.println("error(UserManagementSB-findUserByRut): "+e.getMessage());
        }
        return res;
    }    
    
    @Override
    public UserDTO findUserByUserName(String username){        
        Query q = this.em.createNamedQuery("User.findByUserName", User.class);
        q.setParameter("username", username);
        UserDTO res = null;
        try {
            res = new UserDTO((User) q.getSingleResult());
        } catch (NoResultException e) {
            System.out.println("error(UserManagementSB-findUserByRut): "+e.getMessage());
        }
        return res;
    }

    
    
}
