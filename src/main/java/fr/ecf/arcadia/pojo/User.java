package fr.ecf.arcadia.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String lastname;

    private String firstname;

    private String roles;

    public User(String username, String password, String lastname, String firstname, String roles) {

        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.roles = roles;

    }

    public User() {

    }

}
