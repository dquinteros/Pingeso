/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.studentManagement;

import entity.User;
import entity.UserType;
import java.util.Date;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pingeso
 */
public class StudentManagementSBTest {

    @PersistenceContext(unitName = "Amasy-ejbPU")
    private EntityManager em;
    @EJB
    StudentManagementSB smsb;
    @EJB
    User user;
    @EJB
    UserType ut;

    public StudentManagementSBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        smsb = new StudentManagementSB();
        user = new User();
        ut = new UserType();
        user.setCellPhone("82622385");
        user.setEmail("mail@gmail.com");
        user.setFingerPrint(null);
        user.setFirstName("Karín");
        user.setHomePhone("22222222");
        user.setLastName("Acevedo");
        user.setPassword("4d186321c1a7f0f354b297e8914ab240");
        user.setRut(173161395);
        user.setUserName("karingi");
        ut.setName("Alumno");
        user.setUserType(ut);



    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllStudent method, of class StudentManagementSB.
     */
//    @Test
//    public void testGetAllStudent() throws Exception {
//        System.out.println("getAllStudent");
//   
//    }
    /**
     * Test of insertNewStudent method, of class StudentManagementSB.
     */
    @Test
    public void testInsertNewStudent() throws Exception {
        System.out.println("insertNewStudent");
        boolean result = smsb.insertNewStudent(user, new Date()); 
        assertEquals(true, result);
        result = smsb.insertNewStudent(user, new Date());
        assertEquals(false, result);
        result = smsb.insertNewStudent(null, null);
        assertEquals(false, result);
    }
}