package fr.ecf.arcadia.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ecf.arcadia.Services.ContactService;
import fr.ecf.arcadia.pojo.Contact;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.CONTACT)
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public String sendMail(@RequestBody Contact contact) {
        return contactService.sendMail(contact);
    }
    
}
