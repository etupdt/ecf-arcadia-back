package fr.ecf.arcadia.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ecf.arcadia.pojo.Contact;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendMail(Contact contact) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("arcadia@test.com");
        message.setSubject(contact.getTitle());
        message.setText(contact.getDescription());

        mailSender.send(message);
        
        return "ok";

    }
}
