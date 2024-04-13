package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ecf.arcadia.pojo.User;
import fr.ecf.arcadia.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    public UserServiceImpl () {
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User addUser(User User) {
        return repository.save(User);
    }
    
    @Override
    public User getUser(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User updateUser(User newUser, Long id) {
        
        return repository.findById(id)
        .map(User -> {
            User.setEmail(newUser.getEmail());
            User.setPassword(newUser.getPassword());
            User.setFirstname(newUser.getFirstname());
            User.setLastname(newUser.getLastname());
            return repository.save(User);
        })
        .orElseGet(() -> {
            // newUser.setId(id);
            return repository.save(newUser);
        });
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
    
}
