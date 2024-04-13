package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ecf.arcadia.Services.User2Service;
import fr.ecf.arcadia.pojo.User2;

@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.USER)
public class User2Controller {

    @Autowired
    private User2Service User2Service;

    @GetMapping
    public List<User2> getAllUser2s() {
        return User2Service.getAllUser2s();
    }

    @PostMapping
    public User2 newUser2(@RequestBody User2 User2) {
        return User2Service.addUser2(User2);
    }
    
    @GetMapping("/{id}")
    public User2 one(@PathVariable Long id) {      
        return User2Service.getUser2(id);
    }

    @PutMapping("/{id}")
    public User2 uodateUser2(@RequestBody User2 User2, @PathVariable Long id) {        
        return User2Service.updateUser2(User2, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser2(@PathVariable Long id) {
        User2Service.deleteUser2(id);
    }
    
}
