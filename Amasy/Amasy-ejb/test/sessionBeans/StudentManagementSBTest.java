/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import sessionBeans.studentManagement.StudentManagementSB;
import sessionBeans.studentManagement.StudentManagementSBLocal;
import java.util.LinkedList;
import javax.ejb.embeddable.EJBContainer;
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of persist method, of class StudentManagementSB.
     */
    @Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        Object object = null;
        StudentManagementSB instance = new StudentManagementSB();
        instance.persist(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllStudent method, of class StudentManagementSB.
     */
    @Test
    public void testGetAllStudent() throws Exception {
        System.out.println("getAllStudent");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        StudentManagementSBLocal instance = (StudentManagementSBLocal)container.getContext().lookup("java:global/classes/StudentManagementSB");
        LinkedList expResult = null;
        LinkedList result = instance.getAllStudent();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}