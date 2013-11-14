/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

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
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        FingerprintManagementSBLocal instance = (FingerprintManagementSBLocal)container.getContext().lookup("java:global/classes/FingerprintManagementSB");
        User expResult = null;
        User result = instance.validateFingerprintBM();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}