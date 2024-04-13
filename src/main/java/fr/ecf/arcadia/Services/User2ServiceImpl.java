package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ecf.arcadia.pojo.User2;
import fr.ecf.arcadia.repositories.User2Repository;

@Service
public class User2ServiceImpl implements User2Service {

    @Autowired
    private User2Repository repository;

    public User2ServiceImpl () {
    }

    @Override
    public List<User2> getAllUser2s() {
        return repository.findAll();
    }

    @Override
    public User2 addUser2(User2 User2) {
        return repository.save(User2);
    }
    
    @Override
    public User2 getUser2(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User2 updateUser2(User2 newUser2, Long id) {
        
        return repository.findById(id)
        .map(User2 -> {
            User2.setUsername(newUser2.getUsername());
            User2.setPassword(newUser2.getPassword());
            User2.setFirstname(newUser2.getFirstname());
            User2.setLastname(newUser2.getLastname());
            return repository.save(User2);
        })
        .orElseGet(() -> {
            newUser2.setId(id);
            return repository.save(newUser2);
        });
    }

    @Override
    public void deleteUser2(Long id) {
        repository.deleteById(id);
    }
    
}
