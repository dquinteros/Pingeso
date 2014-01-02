/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import DTOs.ResponseAssistanceDTO;
import DTOs.UserAssistantBlockClassDTO;
import DTOs.UserDTO;
import entity.Assistance;
import entity.AssistanceState;
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
import javax.ejb.EJB;
import javax.ejb.LocalBean;
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
                System.out.println("rollbakc:" + e);
                return false;
            }
        } catch (NotSupportedException | SystemException ex) {
            Logger.getLogger(TakeAttendanceSB.class.getName()).log(Level.SEVERE, null, ex); // Rollback the transaction
            System.out.println("rollbakc:" + ex);
            return false;
        }
    }

    /**
     *
     * @param course
     * @param blockClass
     * @return
     */
    @Override
    public ArrayList<UserAssistantBlockClassDTO> listOfStudentsPerCourseList(long course, long blockClass) {
        Collection<Student> res = em.find(Course.class, course).getListStudent();
        ArrayList<UserAssistantBlockClassDTO> listTakeAttendanceDataUser = new ArrayList<>();
        Assistance assistance;
        UserAssistantBlockClassDTO takeAttendanceDataUserTemp;
        User userTemp;
        for (Student iter : res) {
            try {
                if (existAssistance(iter.getId(), blockClass)) {
                    userTemp = ((Student) em.find(Student.class, iter.getId())).getUser();
                    Query q = this.em.createNamedQuery("Assistance.findAssistanceOfBlockIdClassAndIdStudent");
                    q.setParameter("idStudent", iter.getId());
                    q.setParameter("idBlockClass", blockClass);
                    assistance = (Assistance) q.getSingleResult();
                    if (assistance.getState().getId() == 2) {
                        takeAttendanceDataUserTemp = new UserAssistantBlockClassDTO();
                        takeAttendanceDataUserTemp.TakeAttendanceDataUser(new UserDTO(iter.getUser()), true);
                        listTakeAttendanceDataUser.add(takeAttendanceDataUserTemp);
                    } else {
                        takeAttendanceDataUserTemp = new UserAssistantBlockClassDTO();
                        takeAttendanceDataUserTemp.TakeAttendanceDataUser(new UserDTO(iter.getUser()), false);
                        listTakeAttendanceDataUser.add(takeAttendanceDataUserTemp);
                    }
                } else {
                    takeAttendanceDataUserTemp = new UserAssistantBlockClassDTO();
                    takeAttendanceDataUserTemp.TakeAttendanceDataUser(new UserDTO(iter.getUser()), false);
                    listTakeAttendanceDataUser.add(takeAttendanceDataUserTemp);
                }
            } catch (NoResultException nre) {
                System.out.println(nre);
                return null;
            }
        }
        return listTakeAttendanceDataUser;
    }

    private boolean existAssistance(long idStudent, long idBlockClass) {
        Long response;
        Query q = this.em.createNamedQuery("Assistance.findStudentAssistance");
        q.setParameter("idBlockClass", idBlockClass);
        q.setParameter("idStudent", idStudent);
        response = (Long) q.getSingleResult();
        if (response == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * @param course
     * @return
     */
    @Override
    public BlockClass getIdBlockClassForTakeAttendance(Long course) {
        Collection<BlockClass> res = em.find(Course.class, course).getListBlockClass();
        if (res.isEmpty()) {
            return null;
        }
        for (BlockClass iter : res) {
            if (validAssistanceTimebox(course, iter)) {
                return iter;
            }
        }
        return null;
    }

    private boolean validAssistanceTimebox(Long idCourse, BlockClass blockclass) {
        Course course = em.find(Course.class, idCourse);
        Date date = new Date();
        if (blockclass.getDate().getTime() / 1000 - course.getMinutesBeforeClassStart() * 60 <= date.getTime() / 1000 && date.getTime() / 1000 <= blockclass.getDate().getTime() / 1000 + course.getMinutesAfterClassStart() * 60) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param fingerprint
     * @param blockClass
     * @return
     */
    @Override
    public ResponseAssistanceDTO validateFingerprintBM(String fingerprint, BlockClass blockClass) {
        LinkedList<User> listUser = findUserByBlockClass(blockClass.getId());
        ResponseAssistanceDTO responseAssistanceDTO = new ResponseAssistanceDTO();
        if (listUser != null) {
            User user;
            UserDTO userDTO;
            Student student;
            Long response;
            response = existingStudentList(fingerprint, listUser);
            if (response < 0) {
                responseAssistanceDTO.setAnswer(new AnswerDTO(114));
                return responseAssistanceDTO;
            }
            user = em.find(User.class, response);
            userDTO = new UserDTO(user);
            student = findStundetByUserId(user.getId());
            responseAssistanceDTO = new ResponseAssistanceDTO(userDTO);
            if (addStudentAssistance(student, blockClass)) {
                responseAssistanceDTO.setAnswer(new AnswerDTO(115));
            } else {
                responseAssistanceDTO.setAnswer(new AnswerDTO(116));
            }
            return responseAssistanceDTO;
        } else {
            responseAssistanceDTO.setAnswer(new AnswerDTO(117));
            return responseAssistanceDTO;
        }
    }

    /**
     *
     * @param fingerprint
     * @param blockClass
     * @return
     */
    @Override
    public ResponseAssistanceDTO setAssistance(String rut, Long idBlockClass, String state) {
        LinkedList<User> listUser = findUserByBlockClass(idBlockClass);
        BlockClass blockClass = em.find(BlockClass.class, idBlockClass);
        ResponseAssistanceDTO responseAssistanceDTO = new ResponseAssistanceDTO();
        boolean st = true;
        if (state.equals("Ausente")) {
            st = false;
        }
        if (listUser != null) {
            User user;
            UserDTO userDTO;
            Student student;
            Long response;
            response = getUserByRut(rut);
            if (response < 0) {
                responseAssistanceDTO.setAnswer(new AnswerDTO(114));
                return responseAssistanceDTO;
            }
            user = em.find(User.class, response);
            userDTO = new UserDTO(user);
            student = findStundetByUserId(user.getId());
            responseAssistanceDTO = new ResponseAssistanceDTO(userDTO);
            if (addStudentAssistance(student, blockClass, st)) {
                if(st){
                    responseAssistanceDTO.setAnswer(new AnswerDTO(115));
                }else{
                    responseAssistanceDTO.setAnswer(new AnswerDTO(134));
                }                
            } else {
                responseAssistanceDTO.setAnswer(new AnswerDTO(116));
            }
            return responseAssistanceDTO;
        } else {
            responseAssistanceDTO.setAnswer(new AnswerDTO(117));
            return responseAssistanceDTO;
        }
    }

    private Long existingStudentList(String fingerprint, LinkedList<User> listUser) {
        Long response;
        LinkedList<String> listFingerprint = new LinkedList<>();
        LinkedList<Long> listIdUser = new LinkedList<>();
        for (User it : listUser) {
            if (it.getFingerPrint() != null && !"".equals(it.getFingerPrint())) {
                listFingerprint.add(it.getFingerPrint());
                listIdUser.add(it.getId());
            }
        }
        if (listFingerprint.isEmpty()) {
            return -1L;
        }
        response = FingerprintManagementSB.userIdentify(fingerprint, listFingerprint, listIdUser);
        return response;
    }

    private Long getUserByRut(String rut) {
        System.out.println(rut);
        Query q = em.createNamedQuery("User.findByRut", User.class);
        q.setParameter("rut", Integer.parseInt(rut));
        User user = (User) q.getSingleResult();
        return user.getId();
    }

    private boolean addStudentAssistance(Student student, BlockClass blockClass, boolean state) {
        if (!existAssistance(student, blockClass)) {
            AssistanceState assistanceState;
            if (state) {
                assistanceState = em.find(AssistanceState.class, 3L);
            } else {
                assistanceState = em.find(AssistanceState.class, 1L);
            }
            Assistance assistance = new Assistance();
            assistance.setBlockClass(blockClass);
            assistance.setStudent(student);
            assistance.setState(assistanceState);
            persistInsert(assistance);
            return true;
        } else {
            Query q = em.createNamedQuery("Assistance.findAssistanceOfBlockIdClassAndIdStudent");
            q.setParameter("idBlockClass", blockClass.getId());
            q.setParameter("idStudent", student.getId());
            Assistance assistanceAux = (Assistance) q.getSingleResult();
            Assistance assistance = em.find(Assistance.class, assistanceAux.getId());

            AssistanceState assistanceState;
            if (state) {                
                assistanceState = em.find(AssistanceState.class, 3L);
            } else {
                assistanceState = em.find(AssistanceState.class, 1L);
            }
            assistance.setState(assistanceState);
            System.out.println("hola "+state+"   asd"+assistance.getId());
            persistUpdate(assistance);
            return true;
        }
    }

    private boolean addStudentAssistance(Student student, BlockClass blockClass) {
        if (!existAssistance(student, blockClass)) {
            AssistanceState assistanceState = em.find(AssistanceState.class, 2L);
            Assistance assistance = new Assistance();

            assistance.setBlockClass(blockClass);

            assistance.setStudent(student);

            assistance.setState(assistanceState);

            persistInsert(assistance);

            return true;
        } else {
            Query q = em.createNamedQuery("Assistance.findAssistanceOfBlockIdClassAndIdStudent");
            q.setParameter("idBlockClass", blockClass.getId());
            q.setParameter("idStudent", student.getId());
            Assistance assistanceAux = (Assistance) q.getSingleResult();
            Assistance assistance = em.find(Assistance.class, assistanceAux.getId());
            if (assistance.getState()
                    .getId() == 1L) {
                AssistanceState assistanceState = em.find(AssistanceState.class, 2L);
                assistance.setState(assistanceState);
                persistUpdate(assistance);
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean existAssistance(Student student, BlockClass blockClass) {
        Long response;
        Query q = this.em.createNamedQuery("Assistance.findStudentAssistance");
        q.setParameter("idBlockClass", blockClass.getId());
        q.setParameter("idStudent", student.getId());
        response = (Long) q.getSingleResult();
        if (response == 0) {
            return false;
        } else {
            return true;
        }
    }

    private Student findStundetByUserId(Long userId) {
        Student response;
        Query q = this.em.createNamedQuery("Student.findByIdUser");
        q.setParameter("idUser", userId);
        response = (Student) q.getSingleResult();
        return response;
    }

    private LinkedList<User> findUserByBlockClass(Long idBlockClass) {
        LinkedList<User> listUser = new LinkedList<>();
        Query q = this.em.createNamedQuery("BlockClass.findStudentByBlockClass");
        q.setParameter("idBlockClass", idBlockClass);
        Collection<Student> result;
        try {
            result = (Collection<Student>) q.getResultList();
            for (Student it1 : result) {
                listUser.add(it1.getUser());
            }
            return listUser;
        } catch (NoResultException nre) {
            System.out.println(nre);
            return null;
        }
    }

    @Override
    public CourseDTO getCourseByIdBlockClass(Long idBlockClass) {
        Course course = em.find(BlockClass.class, idBlockClass).getCourse();
        return new CourseDTO(course);
    }
    
    
}
