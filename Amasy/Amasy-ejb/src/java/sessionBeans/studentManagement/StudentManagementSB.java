/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.studentManagement;

import DTOs.NewUserDTO;
import DTOs.UserDTO;
import DTOs.AnswerDTO;
import entity.Student;
import entity.User;
import entity.UserType;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private static final int PASSWORD_LENGTH=10;
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
    public AnswerDTO insertNewStudent(NewUserDTO userDTO, Date enrollYear) {           
        AnswerDTO existEmailUserNameRut = validateStudentRegistry(userDTO);
        if(!existEmailUserNameRut.isValid()){
            return existEmailUserNameRut;
        }        
        String password = newPass(userDTO.getFingerprint());
        User user = newUser(userDTO, password);        
        Student newStudent = new Student();
        newStudent.setUser(user);
        newStudent.setIncomeYear(enrollYear);
        
        //em.getTransaction().begin();
        em.persist(user);
        em.persist(newStudent);
        //em.getTransaction().commit();
        //em.close();        
        //correo
        return new AnswerDTO("000");
    }

    private AnswerDTO validateStudentRegistry(NewUserDTO userDTO){
        if (userDTO == null) {
            return new AnswerDTO("109");
        }
        boolean existEmail = existEmail(userDTO.getEmail());
        boolean existUserName = existUserName(userDTO.getUserName());
        boolean existRut = existRut(userDTO.getRut());
        if(existEmail&&existUserName&&existRut){
            return new AnswerDTO("101");
        }else if(existEmail&&existUserName){
            return new AnswerDTO("102");
        }else if(existUserName&&existRut){            
            return new AnswerDTO("103");
        }else if(existEmail&&existRut){
            return new AnswerDTO("104");
        }else if(existEmail){            
            return new AnswerDTO("105");
        }else if(existUserName){
            return new AnswerDTO("106");
        }else if(existRut){
            return new AnswerDTO("107");
        }
        return new AnswerDTO("000");
    }
    
    private boolean existEmail(String email){
        Long count;
        Query q = em.createNamedQuery("User.countUserByEmail", User.class);
        q.setParameter("email", email);
        count = (Long)q.getSingleResult();
        if(count==0){
            return false;
        }else{
            return true;
        }        
    }
    
    private boolean existUserName(String username){
        Long count;
        Query q = em.createNamedQuery("User.countUserByUserName", User.class);
        q.setParameter("username", username);
        count = (Long)q.getSingleResult();
        if(count==0){
            return false;
        }else{
            return true;
        }        
    }
    
    private boolean existRut(int rut){
        Long count;
        Query q = em.createNamedQuery("User.countUserByRut", User.class);
        q.setParameter("rut", rut);
        count = (Long)q.getSingleResult();
        if(count==0){
            return false;
        }else{
            return true;
        }        
    }
    
    
    
    private User newUser(NewUserDTO userDTO, String password){        
        User newUser = new User();
        newUser.setCellPhone(userDTO.getCellPhone());
        newUser.setEmail(userDTO.getEmail());
        newUser.setFingerPrint(userDTO.getFingerprint());
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setHomePhone(userDTO.getHomePhone());
        newUser.setLastName(userDTO.getLastName());
        newUser.setRut(userDTO.getRut());
        newUser.setUserName(userDTO.getUserName());    
        
        UserType ut = new UserType();
        ut.setName(userDTO.getUserType());
                
        newUser.setPassword(MD5(password));
        
        newUser.setUserType(ut);        
        return newUser;
    }
    
    private String newPass(String fingerprint){        
        String password = null;
        int fingerprintLength = fingerprint.length();
        String halfFingerprint = fingerprint.substring(fingerprintLength/4, fingerprintLength/2);//revisar en caso de error                
        if(halfFingerprint.length()>PASSWORD_LENGTH){
            int random = (int)Math.random()*(halfFingerprint.length()-(PASSWORD_LENGTH+1));
            password = halfFingerprint.substring(random,random+PASSWORD_LENGTH);                
        }else{
            while(halfFingerprint.length()<=PASSWORD_LENGTH){
                halfFingerprint += halfFingerprint;
            }
            int random = (int)Math.random()*(halfFingerprint.length()-(PASSWORD_LENGTH+1));
            password = halfFingerprint.substring(random,random+PASSWORD_LENGTH);
        }    
        return password;
    }
    
    //http://stackoverflow.com/questions/415953/generate-md5-hash-in-java
    //
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
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    
}