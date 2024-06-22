package fr.ecf.arcadia.Services;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import lombok.Getter;
import lombok.Setter;

// @Component("appMailService")
@Service
@Getter
@Setter
public class AppMailService { 
 
    @Autowired
    private Environment environment;    

    Properties prop = new Properties();
    private Session session;

    public AppMailService() {}

    public Session getSession() {

        System.out.println("================================> connect mail." + environment.getProperty("mail.smtp.username") + "     " + environment.getProperty("mail.smtp.host"));

        prop.put("mail.smtp.username", environment.getProperty("mail.smtp.username"));
        prop.put("mail.smtp.password", environment.getProperty("mail.smtp.password"));
        prop.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        prop.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
        prop.put("mail.smtp.host", environment.getProperty("mail.smtp.host"));
        prop.put("mail.smtp.port", environment.getProperty("mail.smtp.port"));
        prop.put("mail.smtp.ssl.trust", environment.getProperty("mail.smtp.ssl.trust"));
        // prop.put("mail.smtp.auth.mechanisms", "LOGIN");
        // prop.put("mail.smtp.auth.mechanisms", "smtp");
    
        // prop.put("spring.mail.password", "Weddingcard.1");
        // prop.put("spring.mail.password", "Weddingcard.1");
        // // prop.put("spring.mail.port", "587");
        // prop.put("spring.mail.properties.mail.smtp.starttls.enable", true);
        // prop.put("spring.mail.properties.mail.smtp.auth", true);
        // prop.put("spring.mail.properties.mail.smtp.starttls.required", true);

        // spring.mail.properties.mail.smtp.starttls.required=true
        this.session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("================================> connect mail." + environment.getProperty("mail.smtp.username") + "     " + environment.getProperty("mail.smtp.password"));
                return new PasswordAuthentication(environment.getProperty("mail.smtp.username"), environment.getProperty("mail.smtp.password"));
            }
        }); 

        return this.session;

    }        

}
