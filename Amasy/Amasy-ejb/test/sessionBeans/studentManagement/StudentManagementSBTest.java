/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.studentManagement;

import entity.User;
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
public class StudentManagementSBTest {

    @EJB
    StudentManagementSB smsb;
    @EJB
    User user;

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
        user.setCellPhone(null);
        user.setEmail(null);
        user.setFingerPrint(null);
        user.setFirstName(null);
        user.setHomePhone(null);
        user.setId(null);
        user.setLastName(null);
        user.setPassword(null);
        user.setRut(173161395);
        user.setUserName(null);
        user.setUserType(null);



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

        assertEquals(true, smsb.insertNewStudent(user));
        assertEquals(false, smsb.insertNewStudent(user));
        assertEquals(false, smsb.insertNewStudent(null));


    }
}