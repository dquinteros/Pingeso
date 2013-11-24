/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.fingerPrint;

import com.digitalpersona.uareu.Fmd;
import entity.BlockClass;
import entity.User;
import javax.ejb.EJB;
/*import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;*/

/**
 *
 * @author Pingeso
 */
public class FingerprintManagementSBTest {

    @EJB
    private FingerprintManagementSB fpmsb = new FingerprintManagementSB();

    public FingerprintManagementSBTest() {
    }

  //  @BeforeClass
    public static void setUpClass() {
    }

  //  @AfterClass
    public static void tearDownClass() {
    }

 //   @Before
    public void setUp() {
    }

//    @After
    public void tearDown() {
    }

    
    /**
     * Test of validateFingerprintBM method, of class FingerprintManagementSB.
     */
 //   @Test
    public void testValidateFingerprintBM_String_BlockClass() throws Exception {
        System.out.println("validateFingerprintBM");
        String s = "";
        BlockClass blockClass = null;
        User expResult = null;
        User result = fpmsb.validateFingerprintBM(s, blockClass);
    //    assertEquals(expResult, result);
    }

    /**
     * Test of findUserByFingerprint method, of class FingerprintManagementSB.
     */
  //  @Test
    public void testFindUserByFingerprint() throws Exception {
        System.out.println("findUserByFingerprint");
        String fingerPrint = "";
        User expResult = null;
        User result = fpmsb.findUserByFingerprint(fingerPrint);
  //      assertEquals(expResult, result);
    }

    /**
     * Test of selectAllFingerprints method, of class FingerprintManagementSB.
     */
  //  @Test
    public void testSelectAllFingerprints() throws Exception {
        System.out.println("selectAllFingerprints");
    }

    /**
     * Test of hexStringToByteArray method, of class FingerprintManagementSB.
     */
   // @Test
    public void testHexStringToByteArray() throws Exception {
        System.out.println("hexStringToByteArray");
        byte[] a200 = new byte[2];
        a200[0] = (byte) (((byte) 10 << 4) + (byte) 2);
        a200[1] = (byte) (((byte) 12 << 4) + (byte) 3);

        byte[] result = fpmsb.hexStringToByteArray("a2c3");
   //     assertArrayEquals(a200, result);
    }

    /**
     * Test of fmdToString method, of class FingerprintManagementSB.
     */
  //  @Test
    public void testFmdToString() throws Exception {
        System.out.println("fmdToString");
        Fmd f = null;
        String expResult = "";
        String result = fpmsb.fmdToString(f);
  //      assertEquals(expResult, result);
    }

    /**
     * Test of byteArrayToHexString method, of class FingerprintManagementSB.
     */
  //  @Test
    public void testByteArrayToHexString() throws Exception {
        System.out.println("byteArrayToHexString");
        byte[] a200 = new byte[2];
        a200[0] = (byte) (((byte) 10 << 4) + (byte) 2);
        a200[1] = (byte) (((byte) 12 << 4) + (byte) 3);
        String expResult = "a2c3";
        String result = fpmsb.byteArrayToHexString(a200);
  //      assertEquals(expResult, result);
    }

    /**
     * Test of stringToFmd method, of class FingerprintManagementSB.
     */
 //   @Test
    public void testStringToFmd() throws Exception {
        System.out.println("stringToFmd");
      
    }
}