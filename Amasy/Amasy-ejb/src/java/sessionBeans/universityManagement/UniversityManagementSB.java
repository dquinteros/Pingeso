package sessionBeans.universityManagement;

import DTOs.AnswerDTO;
import DTOs.ListUniversityDTO;
import DTOs.UniversityDTO;
import entity.University;
import java.util.Collection;
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

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UniversityManagementSB implements UniversityManagementSBLocal {

    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;
    @Resource
    UserTransaction ut;
    
    /**
     *
     * @param object
     */
    public void persist(Object object) {
        em.persist(object);
    }

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
     * @param object
     * @return
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean persistUpdate(Object object) {
        try {
            ut.begin(); // Start a new transaction
            try {
                em.merge(object);
                ut.commit(); // Commit the transaction
                return true;
            } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException e) {
                ut.rollback(); // Rollback the transaction
                System.out.println("rollback:" + e);
                return false;
            }
        } catch (NotSupportedException | SystemException ex) {
            Logger.getLogger(TakeAttendanceSB.class.getName()).log(Level.SEVERE, null, ex); // Rollback the transaction
            System.out.println("rollback:" + ex);
            return false;
        }
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public ListUniversityDTO getAllUniversity(){
        Collection<UniversityDTO> result;
        Query q = this.em.createNamedQuery("University.getAllUniversities");
        try {
            result = (Collection<UniversityDTO>) q.getResultList();
            return new ListUniversityDTO(result, new AnswerDTO(0));
        } catch (NoResultException nre) {
            System.out.println(nre);
            return null;
        }
    }
    
    @Override    
    public AnswerDTO insertNewUniversity(UniversityDTO university, String rutTemp){
        if(university == null){
            return new AnswerDTO(137);
        }
        Query q = em.createNamedQuery("University.countUniversitiesByName", University.class);
        q.setParameter("name", university.getName());
        Long count = (Long) q.getSingleResult();
        if(count!=0){
            return new AnswerDTO(138);
        }
        university.setRut(Integer.parseInt(rutTemp));
        
        persistInsert(newUniversity(university));
        return new AnswerDTO(0);
    }
    
    private University newUniversity(UniversityDTO universityDTO){
        University university = new University();
        university.setAddress(universityDTO.getAddress());
        university.setName(universityDTO.getName());
        university.setRut(universityDTO.getRut());
        university.setUniversityStatus(true);
        return university;
    }
    
    @Override
    public UniversityDTO getUniversityById(Long universityId){
        University university = em.find(University.class, universityId);
        return new UniversityDTO(university);
    }
    
    @Override
    public AnswerDTO updateUniversity(UniversityDTO newUniversity, Long universityId){
        University university = em.find(University.class, universityId);
        university.setAddress(newUniversity.getAddress());
        university.setName(newUniversity.getName());
        if(persistUpdate(university)){
            return new AnswerDTO(0);
        }else{
            return new AnswerDTO(139);
        } 
    }
    
    @Override
    public AnswerDTO deleteUniversity(Long id){
        University university = em.find(University.class, id);
        university.setUniversityStatus(false);
        university.setListCourse(null);
        university.setListStudent(null);
        persistUpdate(university);
        return new AnswerDTO(0);
    }
    
}
