/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DTOs.AnswerDTO;
import DTOs.ResponseAssistanceDTO;
import DTOs.UserAssistantBlockClassDTO;
import DTOs.UserDTO;
import entity.Assistance;
import entity.BlockClass;
import entity.Course;
import entity.Student;
import entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import sessionBeans.fingerprint.FingerprintManagementSB;

/**
 *
 * @author Pingeso
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TakeAttendanceSB implements TakeAttendanceSBLocal {
    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;
    @Resource
    UserTransaction ut;

    public void persist(Object object) {
        em.persist(object);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean persistInsert(Object object){
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

    @Override
    public ArrayList<UserAssistantBlockClassDTO> listOfStudentsPerCourseList(long course, long blockClass) {
        Collection<Student> res = em.find(Course.class, course).getListStudent();
        ArrayList<UserAssistantBlockClassDTO> listTakeAttendanceDataUser = new ArrayList<> ();
        Long resultado;
        UserAssistantBlockClassDTO takeAttendanceDataUserTemp;
        User userTemp;
        for(Student iter : res){
            userTemp = ((Student)em.find(Student.class, iter.getId())).getUser();
            Query q = this.em.createNamedQuery("Assistance.findStudentAssistance");
            q.setParameter("idStudent", iter.getId());
            q.setParameter("idBlockClass", blockClass);
            try {
                resultado = (Long)q.getSingleResult();
                if(resultado==1){
                    takeAttendanceDataUserTemp = new UserAssistantBlockClassDTO();
                    takeAttendanceDataUserTemp.TakeAttendanceDataUser(new UserDTO(iter.getUser()), true);
                    listTakeAttendanceDataUser.add(takeAttendanceDataUserTemp);
                }else{
                    takeAttendanceDataUserTemp = new UserAssistantBlockClassDTO();
                    takeAttendanceDataUserTemp.TakeAttendanceDataUser(new UserDTO(iter.getUser()), false);
                    listTakeAttendanceDataUser.add(takeAttendanceDataUserTemp);
                }
            }
            catch (NoResultException nre) {
                System.out.println(nre);
                return null;
            }
        }
        return listTakeAttendanceDataUser;
    }

    @Override
    public BlockClass getIdBloackClassForTakeAttendance(Long course) {
        Date date = new Date();
        Collection<BlockClass> res = em.find(Course.class, course).getListBlockClass();
        if(res.isEmpty()){
            return null;
        }
        for (BlockClass iter : res) {
            if( iter.getDate().getTime()/1000 -5*60 <= date.getTime()/1000 && date.getTime()/1000 <= iter.getDate().getTime()/1000+30*60){
                return iter;
            }
        }
        return null;
    }

    @Override
    public ResponseAssistanceDTO validateFingerprintBM(String fingerprint, BlockClass blockClass){
        LinkedList<User> listUser = findUserByBlockClass(blockClass.getId());
        ResponseAssistanceDTO responseAssistanceDTO = new ResponseAssistanceDTO();
        if(listUser!=null){
            User user;                      
            UserDTO userDTO;
            Student student;
            Long response;
            response = existingStudentList(fingerprint, listUser);
            
            if(response<0){
                responseAssistanceDTO.setAnswer(new AnswerDTO(114));
                return responseAssistanceDTO;
            }
            user = em.find(User.class, response);
            userDTO = new UserDTO(user);
            student = findStundetByUserId(user.getId());
            responseAssistanceDTO = new ResponseAssistanceDTO(userDTO);
            if(addStudentAssistance(student, blockClass)){                
                responseAssistanceDTO.setAnswer(new AnswerDTO(115));
            }else{
                responseAssistanceDTO.setAnswer(new AnswerDTO(116));
            }
            return responseAssistanceDTO;
        }else{
            responseAssistanceDTO.setAnswer(new AnswerDTO(117));
            return responseAssistanceDTO;
        }
    }

    private Long existingStudentList(String fingerprint, LinkedList<User> listUser){
        Long response;  
        LinkedList<String> listFingerprint = new LinkedList<>();
        LinkedList<Long> listIdUser = new LinkedList<>();

        for(User it: listUser){            
            if(it.getFingerPrint()!=null){
                listFingerprint.add(it.getFingerPrint());
                listIdUser.add(it.getId());          
                System.out.println("id usuario: "+it.getId()+" fingerprint user: "+it.getFingerPrint());                                
            }            
        }
        System.out.println("fingerprint buscado: "+fingerprint);
        response = FingerprintManagementSB.userIdentify(fingerprint, listFingerprint, listIdUser);
        return response;
    }

    private boolean addStudentAssistance(Student student, BlockClass blockClass){
        if(!existAssistance(student, blockClass)){
            Assistance assistance = new Assistance();
            Date date = new Date();
            assistance.setBlockClass(blockClass);
            assistance.setStudent(student);
            assistance.setDate(date);
            persistInsert(assistance);
            return true;
        }else{
            return false;
        }
    }


    private boolean existAssistance(Student student, BlockClass blockClass){
        Long response;
        Query q = this.em.createNamedQuery("Assistance.findStudentAssistance");
        q.setParameter("idBlockClass", blockClass.getId());
        q.setParameter("idStudent", student.getId());
        response = (Long)q.getSingleResult();
        System.out.println("asistencia presente: "+response);
        if(response==0){
            return false;
        }else{
            return true;
        }
    }

    private Student findStundetByUserId(Long userId){
        Student response;
        Query q = this.em.createNamedQuery("Student.findByIdUser");
        q.setParameter("idUser", userId);
        response = (Student)q.getSingleResult();
        return response;
    }

    private LinkedList<User> findUserByBlockClass(Long idBlockClass){
        LinkedList<User> listUser = new LinkedList<>();
        Query q = this.em.createNamedQuery("BlockClass.findStudentByBlockClass");
        q.setParameter("idBlockClass", idBlockClass);
        Collection<Student> result;
        try {
            result = (Collection<Student>)q.getResultList();
            for(Student it1: result){
                listUser.add(it1.getUser());
            }
            return listUser;
        }
        catch (NoResultException nre) {
            System.out.println(nre);
            return null;
        }
    }
}
