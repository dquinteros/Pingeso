/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans.takeAttendance;

import entity.User;
import entity.UserType;
import java.util.Date;
import javax.ejb.EJB;
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
public class addStudentMBTest {
    
    @EJB
    User user;
    @EJB
    UserType ut;
    
    public addStudentMBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
     * Test of insertNewStudent method, of class addStudentMB.
     */
    @Test
    public void testInsertNewStudent() {
        System.out.println("insertNewStudent");
        User u = null;
        Date incomeYear = null;
        addStudentMB instance = new addStudentMB();
        boolean expResult = false;
        boolean result = instance.insertNewStudent(u, incomeYear);
        assertEquals(expResult, result);
   
    }
}