package fr.ecf.arcadia.Services;

import java.util.List;

import fr.ecf.arcadia.pojo.User2;

public interface User2Service {

        public List<User2> getAllUser2s();
        public User2 addUser2(User2 newUser2);
        public User2 getUser2(Long id);
        public User2 updateUser2(User2 newUser2, Long id);
        public void deleteUser2(Long id);

}
