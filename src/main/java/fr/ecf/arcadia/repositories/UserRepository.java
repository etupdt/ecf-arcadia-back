package fr.ecf.arcadia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ecf.arcadia.pojo.User;

public interface UserRepository extends JpaRepository<User, Long>  {

    Optional<User> findByEmail(String email);
}
