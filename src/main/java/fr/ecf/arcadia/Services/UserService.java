package fr.ecf.arcadia.Services;

import java.util.List;

import fr.ecf.arcadia.pojo.User;

public interface UserService {

        public List<User> getAllUsers();
        public User addUser(User newUser);
        public User getUser(Long id);
        public User updateUser(User newUser, Long id);
        public void deleteUser(Long id);

}
