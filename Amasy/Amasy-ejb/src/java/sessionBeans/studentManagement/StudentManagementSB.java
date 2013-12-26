/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.studentManagement;

import DTOs.NewUserDTO;
import DTOs.UserDTO;
import DTOs.AnswerDTO;
import DTOs.CourseDTO;
import DTOs.ListCourseDTO;
import entity.Assistance;
import entity.AssistanceState;
import entity.BlockClass;
import entity.Course;
import entity.Student;
import entity.User;
import entity.UserType;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
public class StudentManagementSB implements StudentManagementSBLocal {

    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;
    @Resource
    UserTransaction ut;
    private static final int PASSWORD_LENGTH = 10;

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
                System.out.println("rollbakc:"+e);
                return false;
            }
        } catch (NotSupportedException | SystemException ex) {
            Logger.getLogger(TakeAttendanceSB.class.getName()).log(Level.SEVERE, null, ex); // Rollback the transaction
            System.out.println("rollbakc:"+ex);
            return false;
        }
    }

    /**
     *
     * @return
     */
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
            studentDTOTemp = new UserDTO(iter);
            exitResult.add(studentDTOTemp);
        }
        return exitResult;
    }

    /**
     *
     * @param userDTO
     * @param enrollYear
     * @return
     */
    @Override
    public AnswerDTO insertNewStudent(NewUserDTO userDTO, Date enrollYear) {
        AnswerDTO existEmailUserNameRut = validateStudentRegistry(userDTO);
        if (!existEmailUserNameRut.isValid()) {
            return existEmailUserNameRut;
        }
        String password = newPass(userDTO.getFingerprint());
        String roll = "Alumno";
        User user = newUser(userDTO, password, roll);
        Student newStudent = new Student();
        newStudent.setUser(user);
        newStudent.setIncomeYear(enrollYear);
        persistInsert(user);
        persistInsert(newStudent);
        return new AnswerDTO(0);
    }

    /**
     *
     * @param newStudent
     * @param studentId
     * @return
     */
    @Override
    public AnswerDTO updateStudent(NewUserDTO newStudent, Long studentId) {
        User user = em.find(User.class, studentId);
        user.setFirstName(newStudent.getFirstName());
        user.setLastName(newStudent.getLastName());
        user.setCellPhone(newStudent.getCellPhone());
        user.setHomePhone(newStudent.getHomePhone());
        if (!"".equals(newStudent.getFingerprint())) {
            user.setFingerPrint(newStudent.getFingerprint());
        }
        if (persistUpdate(user)) {
            return new AnswerDTO(0);
        } else {
            return new AnswerDTO(113);
        }
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public NewUserDTO getStudentById(long userId) {
        User user = em.find(User.class, userId);
        NewUserDTO newUser = new NewUserDTO(user);
        return newUser;
    }

    private AnswerDTO validateStudentRegistry(NewUserDTO userDTO) {
        if (userDTO == null) {
            return new AnswerDTO(109);
        }
        boolean existEmail = existEmail(userDTO.getEmail());
        boolean existUserName = existUserName(userDTO.getUserName());
        boolean existRut = existRut(Integer.parseInt(userDTO.getRut()));
        if (existEmail && existUserName && existRut) {
            return new AnswerDTO(101);
        } else if (existEmail && existUserName) {
            return new AnswerDTO(102);
        } else if (existUserName && existRut) {
            return new AnswerDTO(103);
        } else if (existEmail && existRut) {
            return new AnswerDTO(104);
        } else if (existEmail) {
            return new AnswerDTO(105);
        } else if (existUserName) {
            return new AnswerDTO(106);
        } else if (existRut) {
            return new AnswerDTO(107);
        }
        return new AnswerDTO(000);
    }

    private boolean existEmail(String email) {
        Long count;
        Query q = em.createNamedQuery("User.countUserByEmail", User.class);
        q.setParameter("email", email);
        count = (Long) q.getSingleResult();
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean existUserName(String username) {
        Long count;
        Query q = em.createNamedQuery("User.countUserByUserName", User.class);
        q.setParameter("username", username);
        count = (Long) q.getSingleResult();
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean existRut(int rut) {
        Long count;
        Query q = em.createNamedQuery("User.countUserByRut", User.class);
        q.setParameter("rut", rut);
        count = (Long) q.getSingleResult();
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private User newUser(NewUserDTO userDTO, String password, String roll) {
        User newUser = new User();
        newUser.setCellPhone(userDTO.getCellPhone());
        newUser.setEmail(userDTO.getEmail());
        newUser.setFingerPrint(userDTO.getFingerprint());
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setHomePhone(userDTO.getHomePhone());
        newUser.setLastName(userDTO.getLastName());
        newUser.setRut(Integer.parseInt(userDTO.getRut()));
        newUser.setUserName(userDTO.getUserName());

        UserType ut = new UserType();
        ut.setName(roll);

        newUser.setPassword(MD5(password));

        newUser.setUserType(ut);
        return newUser;
    }

    private String newPass(String fingerprint) {
        String password = null;
        int fingerprintLength = fingerprint.length();
        String halfFingerprint = fingerprint.substring(fingerprintLength / 4, fingerprintLength / 2);//revisar en caso de error                
        if (halfFingerprint.length() > PASSWORD_LENGTH) {
            int random = (int) Math.random() * (halfFingerprint.length() - (PASSWORD_LENGTH + 1));
            password = halfFingerprint.substring(random, random + PASSWORD_LENGTH);
        } else {
            while (halfFingerprint.length() <= PASSWORD_LENGTH) {
                halfFingerprint += halfFingerprint;
            }
            int random = (int) Math.random() * (halfFingerprint.length() - (PASSWORD_LENGTH + 1));
            password = halfFingerprint.substring(random, random + PASSWORD_LENGTH);
        }
        return password;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public AnswerDTO deleteStudent(Long id) {
        System.out.println(id);
        User u = em.find(User.class, id);
        if (u == null) {
            System.out.println("nulo");
            return new AnswerDTO(111);
        } else if (u.isUserStatus() == false) {
            System.out.println("ya cambiado");
            return new AnswerDTO(112);
        } else {
            System.out.println("Bien");
            u.setUserStatus(false);
            persistUpdate(u);
        }
        return new AnswerDTO(000);
    }
    
    /**
     *
     * @param idUser
     * @return
     */
    @Override
    public ListCourseDTO getCoursesFromStudent(Long idUser) {
        Collection<Course> listCourse;
        ListCourseDTO listCourseDTO;
        
        Collection<CourseDTO> collectionCourseDTO = new ArrayList<>();
        
        Query q = em.createNamedQuery("Student.getListCourseFromUser", Student.class);
        q.setParameter("idUser", idUser);
        listCourse = (Collection<Course>) q.getResultList();
        
        for(Course it: listCourse){
            collectionCourseDTO.add(new CourseDTO(it));
        }
                
        listCourseDTO = new ListCourseDTO(collectionCourseDTO, new AnswerDTO(0));
        return listCourseDTO;
    }
    
    /**
     *
     * @param idUser
     * @param idCourse
     * @return
     */
    @Override
    public AnswerDTO enrollStudentOnCourse(Long idUser, Long idCourse) {          
        Student student = getStudentByIdUser(idUser);                     
        if(existStudentOnCourse(student, idCourse)){
            return new AnswerDTO(127);
        }
        student = addCourseToStudent(student, idCourse);        
        LinkedList<Assistance> listAssistance = generateAssistanceToStudent(student, idCourse);  
        System.out.println("asdf2222 "+listAssistance.size());
        return persistEnrollStudentOnCourse(student, listAssistance);
    }
    
    private Student getStudentByIdUser(Long idUser){
        Query q = em.createNamedQuery("Student.findByIdUser", User.class);
        q.setParameter("idUser", idUser);
        return (Student)q.getSingleResult();
    }
    
    private Student addCourseToStudent(Student student, Long idCourse){
        List<Course> listCourse; 
        Course course = em.find(Course.class, idCourse);                 
        listCourse = student.getListCourse();         
        listCourse.add(course);                                   
        student.setListCourse(listCourse);
        return student;
    }
    
    private LinkedList<Assistance> generateAssistanceToStudent(Student student, Long idCourse){
        LinkedList<BlockClass> listBlockClass;
        listBlockClass = new LinkedList<>(em.find(Course.class, idCourse).getListBlockClass());        
        Assistance assistance;
        LinkedList<Assistance> listAssistance = new LinkedList<>();        
        AssistanceState assistanceState = em.find(AssistanceState.class, 1L);
        for(BlockClass it: listBlockClass){
            assistance = new Assistance();
            assistance.setState(assistanceState);
            assistance.setBlockClass(it);
            assistance.setStudent(student);            
            listAssistance.add(assistance);
        }      
        return listAssistance;
    }
    
    private Boolean existStudentOnCourse(Student student, Long idCourse){
        LinkedList<Course> listCourse = new LinkedList<>( student.getListCourse());
        Course course = em.find(Course.class, idCourse);
        return listCourse.contains(course);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private AnswerDTO persistEnrollStudentOnCourse(Student student, LinkedList<Assistance> listAssistance){
        try {
            ut.begin(); // Start a new transaction
            try {
                em.merge(student);
                System.out.println("entro "+listAssistance.size());                
                for(Assistance it: listAssistance){
                    System.out.println(""+it.getBlockClass());
                    System.out.println(""+it.getStudent());
                    em.persist(it);
                }                
                ut.commit(); // Commit the transaction
                return new AnswerDTO(0);
            } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException e) {
                System.out.println("error: "+e);
                ut.rollback(); // Rollback the transaction
                return new AnswerDTO(126);
            }
        } catch (NotSupportedException | SystemException ex) {
            Logger.getLogger(TakeAttendanceSB.class.getName()).log(Level.SEVERE, null, ex); // Rollback the transaction
            return new AnswerDTO(126);
        }
    }

    //http://stackoverflow.com/questions/415953/generate-md5-hash-in-java
    private String MD5(String md5) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    private String regExFix(String ex) {
        if ("".equals(ex)) {
            ex = "%";
        } else {
            ex = ("%".concat(ex)).concat("%");
        }
        System.out.println(ex);
        return ex;
    }
    


    /**
     *
     * @return
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     *
     * @param em
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    

    
}
