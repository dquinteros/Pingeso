/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.teacherManagement;

import DTOs.AnswerDTO;
import DTOs.NewUserDTO;
import DTOs.UserDTO;
import entity.Teacher;
import entity.User;
import entity.UserType;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
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
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
public class TeacherManagementSB implements TeacherManagementSBLocal {
    
    @Resource(name="mail/AmasyDB")
    private Session session;
    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;
    @Resource
    UserTransaction ut;
    private static final int PASSWORD_LENGTH = 10;
    
    public void persist(Object object) {
        em.persist(object);
    }
    
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
                return false;
            }
        } catch (NotSupportedException | SystemException ex) {
            Logger.getLogger(TakeAttendanceSB.class.getName()).log(Level.SEVERE, null, ex); // Rollback the transaction
            return false;
        }
    }
    
    @Override
    public LinkedList<UserDTO> getAllTeacher() {
        Collection<User> result;
        LinkedList<UserDTO> exitResult = new LinkedList<UserDTO>();
        Query q = this.em.createNamedQuery("Teacher.getAllTeacherUserInfo");
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
    
    @Override
    public AnswerDTO insertNewTeacher(NewUserDTO userDTO) {
        AnswerDTO existEmailUserNameRut = validateTeacherRegistry(userDTO);
        if (!existEmailUserNameRut.isValid()) {
            return existEmailUserNameRut;
        }
        String password = newPass(userDTO.getEmail()+userDTO.getRut());
        String roll = "Profesor";
        User user = newUser(userDTO, password, roll);
        Teacher newTeacher = new Teacher();
        newTeacher.setUser(user);
        persistInsert(user);
        persistInsert(newTeacher);
        return new AnswerDTO(0);
    }
    
    private AnswerDTO validateTeacherRegistry(NewUserDTO userDTO) {
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
        
        senEmail(newUser.getEmail(), newUser.getUserName() ,password);
        
        return newUser;
    }

    private boolean senEmail(String recipient, String userName, String password){
        try {
            Message message = new MimeMessage(session);
            message.setFrom();
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));
            message.setSubject("Registro Amasy");
            DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
            Date timeStamp = new Date();
            String text="";
            text += "Estimado profesor.";
            text += "\n\n";
            text += "Usted ha sido registrado exitosamente en el sistema Amasy.";
            text += "\n";
            text += "Sus datos de ingreso son: ";
            text += "\n";
            text += "Nombre de usuario: "+userName;
            text += "\n";
            text += "Contraseña: "+password;
            String messageText = text;
            message.setText(messageText);
            message.setHeader("X-Mailer", "amasys@usach.cl");
            message.setSentDate(timeStamp);
            // Send message
            Transport.send(message);
            System.out.println("Mail sent to " + recipient + ".");
        } catch (MessagingException ex) {
            ex.printStackTrace();
            System.out.println("Error in ConfirmerBean for " + recipient);
        }
        return false;

    }
    
    private String newPass(String emailRut) {
        emailRut = MD5(emailRut+MD5(emailRut));
        String password = null;
        int fingerprintLength = emailRut.length();
        String halfFingerprint = emailRut.substring(fingerprintLength / 4, fingerprintLength / 2);//revisar en caso de error                
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
    
    @Override
    public NewUserDTO getTeacherById(long userId){
        User user = em.find(User.class, userId);
        NewUserDTO newUser = new NewUserDTO(user);
        return newUser;
    }
    
    @Override
    public AnswerDTO updateTeacher(NewUserDTO newTeacher, Long teacherId){
        User user = em.find(User.class, teacherId);
        user.setFirstName(newTeacher.getFirstName());
        user.setLastName(newTeacher.getLastName());
        user.setCellPhone(newTeacher.getCellPhone());
        user.setHomePhone(newTeacher.getHomePhone());
        if(!"".equals(newTeacher.getFingerprint())){
            user.setFingerPrint(newTeacher.getFingerprint());
        }
        if(persistUpdate(user)){
            return new AnswerDTO(0);
        }else{
            return new AnswerDTO(113);
        }       
    }
    
    @Override
    public AnswerDTO deleteTeacher(Long id) {
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
    
}
