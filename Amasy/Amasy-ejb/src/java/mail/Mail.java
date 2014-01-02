/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Pingeso
 */
public class Mail {
    
    public boolean sendMessageCreateUser(String recipientes, String userName, String password){
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
        String title = "Registro Amasy";
        return sendMessage(recipientes, title, text);
    }

    public boolean sendMessage(String recipientes, String title, String text) {
        final String username = "amasyRealm@gmail.com";
        final String password = "amasy123";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("amasyRealm@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientes));
            message.setSubject(title);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
