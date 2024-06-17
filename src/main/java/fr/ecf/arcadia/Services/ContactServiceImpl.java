package fr.ecf.arcadia.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ecf.arcadia.pojo.Contact;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private AppMailService mailService;

    @Override
    public String sendMail(Contact contact) {

        MimeMessage message = new MimeMessage(mailService.getSession());
        System.out.println("================================> Envoi du mail.");
        
        try {

            message.setFrom(new InternetAddress("from@etupdt.fr"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(contact.getEmail()));
            message.setSubject(contact.getTitle());

            String msg = contact.getDescription();

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            return "Le mail a été envoyé.";

        } catch (MessagingException e) {

            System.out.println("================================> Envoi du mail." + e.getMessage());
            return "Erreur lors de l'envoi du mail.";

        }

    }
    
}
