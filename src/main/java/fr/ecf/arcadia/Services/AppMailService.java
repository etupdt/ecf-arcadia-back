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

        prop.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        prop.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
        prop.put("mail.smtp.host", environment.getProperty("mail.smtp.host"));
        prop.put("mail.smtp.port", environment.getProperty("mail.smtp.port"));
        prop.put("mail.smtp.ssl.trust", environment.getProperty("mail.smtp.ssl.trust"));
    
        this.session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("================================> connect mail." + environment.getProperty("mail.smtp.username") + "     " + environment.getProperty("mail.smtp.host"));
                return new PasswordAuthentication(environment.getProperty("mail.smtp.username"), environment.getProperty("mail.smtp.password"));
            }
        }); 

        return this.session;

    }        

}
