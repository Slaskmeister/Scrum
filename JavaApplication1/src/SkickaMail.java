import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
///
/**
 *
 * @author William
 */
public class SkickaMail {
    public static void sendMail(String recepient) throws MessagingException {
    System.out.println("skickar");
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
    String myAccountEmail = "formellblogg@gmail.com";
    String password = "scrum123!";
    
    Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
            
            return new PasswordAuthentication(myAccountEmail, password);
            }     
    });
    
    Message message = prepareMessage(session, myAccountEmail, recepient);
    
    Transport.send(message);
    System.out.println("msg sent");

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
       try {
        
       Message message = new MimeMessage(session);
       message.setFrom(new InternetAddress(myAccountEmail));
       message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
       message.setSubject("Ett nytt inlÃ¤gg har postas");
       message.setText("Detta var ett autoskickat meddelande att en ny post har postas pÃ¥ forumet logga in fÃ¶r kolla. \nDetta meddelande gÃ¥r inte att svara pÃ¥!");
       return message;
       
    } catch (Exception ex) {
        Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        
    }
       return null;
        
        
    }
    
}

 
    