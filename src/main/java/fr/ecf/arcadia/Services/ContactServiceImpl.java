package fr.ecf.arcadia.Services;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import jakarta.mail.*;   // ;.mail.Message;
// import javax.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage; //; .MimeMessage;
// import javax.mail.Transport;
// import javax.mail.internet.InternetAddress;
// import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ecf.arcadia.pojo.Contact;
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private AppMailService mailService;

    public void sendMail(Contact contact) {

        Session session = this.mailService.getSession();

        MimeMessage message = new MimeMessage(session);

        System.out.println("================================> connect mail." + contact.getDescription());

        try {
            message.addHeader("Content-type", "text/PLAIN; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");
            message.setFrom(new InternetAddress(contact.getEmail(), contact.getEmail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("arcadia@etupdt.com", false));
            message.setSubject(contact.getTitle(), "UTF-8");
            message.setText(contact.getDescription(), "UTF-8");
            message.setSentDate(new Date());
            System.out.println("================================> connect mail." + message.getContentType());
            System.out.println("================================> connect mail." + message.toString());

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // return "Votre demande de contact a bien été envoyée !";

    }
}
