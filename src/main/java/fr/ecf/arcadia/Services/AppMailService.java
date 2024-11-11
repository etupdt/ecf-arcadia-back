package fr.ecf.arcadia.Services;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

// import jakarta.mail.Authenticator;
// import jakarta.mail.PasswordAuthentication;
// import jakarta.mail.Session;
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

        System.out.println("================================> connect mail." + environment.getProperty("spring.mail.username") + "     " + environment.getProperty("spring.mail.host"));

        // prop.put("mail.smtp.username", environment.getProperty("spring.mail.username"));
        // prop.put("mail.smtp.password", environment.getProperty("spring.mail.password"));
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", environment.getProperty("spring.mail.host"));
        prop.put("mail.smtp.port", environment.getProperty("spring.mail.port"));
        prop.put("mail.smtp.ssl.trust", environment.getProperty("spring.mail.host"));
        prop.put("mail.smtp.ssl.checkserveridentity", "false");

        this.session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(environment.getProperty("spring.mail.username"), environment.getProperty("spring.mail.password"));
            }
        }); 

        return this.session;

    }        

}
