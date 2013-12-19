/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans.fingerprint;

import javax.ejb.Stateless;
import com.digitalpersona.uareu.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pingeso
 */
@Stateless
public class FingerprintManagementSB {

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
    
    private Fmd stringToFmd(String s) {
        byte[] b = FingerprintManagementSB.hexStringToByteArray(s);
        Fmd f = null;
        try {
            f = UareUGlobal.GetImporter().ImportFmd(b, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
        } catch (UareUException ex) {
            Logger.getLogger(FingerprintManagementSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public static Long userIdentify(String wantedFingerprint, LinkedList<String> fingerList, LinkedList<Long> idUser) {
        Importer importer = UareUGlobal.GetImporter();
        Fmd wantedFmd;
        Fmd[] fmdArray = new Fmd[fingerList.size()];
        int i = 0;
        try {
            wantedFmd = importer.ImportFmd(hexStringToByteArray(wantedFingerprint), Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
            for (String f : fingerList) {
                fmdArray[i] = importer.ImportFmd(hexStringToByteArray(f), Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
                i++;
            }
        } catch (UareUException ex) {
            Logger.getLogger(FingerprintManagementSB.class.getName()).log(Level.SEVERE, null, ex);
            return -1L;
        }
        Long[] idUserArray = new Long[idUser.size()];
        i = 0;
        for (Long in : idUser) {
            idUserArray[i] = in;
            i++;
        }
        Long r = fingerprintIdentify(wantedFmd, fmdArray, i, idUserArray);
        return r;
    }

    private static Long fingerprintIdentify(Fmd f, Fmd[] fa, int fc, Long[] idUser) {        
        Engine engine = UareUGlobal.GetEngine();
        int falsepositive_rate = Engine.PROBABILITY_ONE / 100000;
        Engine.Candidate[] vCandidates = null;
        try {
            vCandidates = engine.Identify(f, 0, fa, falsepositive_rate, fc);
        } catch (UareUException ex) {
            Logger.getLogger(FingerprintManagementSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (vCandidates != null) {
            if (vCandidates.length != 0) {
                System.out.println("funciono");
                System.out.println(idUser[vCandidates[0].fmd_index]);
                return idUser[vCandidates[0].fmd_index];//success

            } else {
                System.out.println("no funciono");
                return -1L;//fail
            }
        }
        return -2L;//vCandidates null
    }
        
    
}