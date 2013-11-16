/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import com.digitalpersona.uareu.Fmd;
import entity.User;
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
public class FingerprintManagementSBTest {
    
    public FingerprintManagementSBTest() {
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
     * Test of validateFingerprintBM method, of class FingerprintManagementSB.
     */
    @Test
    public void testValidateFingerprintBM() throws Exception {
        System.out.println("validateFingerprintBM");
        Fmd fmd1 = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        FingerprintManagementSBLocal instance = (FingerprintManagementSBLocal)container.getContext().lookup("java:global/classes/FingerprintManagementSB");
        User expResult = null;
        User result = instance.validateFingerprintBM(fmd1);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectAllFingerprints method, of class FingerprintManagementSB.
     */
    @Test
    public void testSelectAllFingerprints() throws Exception {
        System.out.println("selectAllFingerprints");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        FingerprintManagementSBLocal instance = (FingerprintManagementSBLocal)container.getContext().lookup("java:global/classes/FingerprintManagementSB");
        Fmd[] expResult = null;
        Fmd[] result = instance.selectAllFingerprints();
        assertArrayEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of persist method, of class FingerprintManagementSB.
     */
    @Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        Object object = null;
        FingerprintManagementSB instance = new FingerprintManagementSB();
        instance.persist(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}