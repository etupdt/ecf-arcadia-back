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
public class User2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String lastname;

    private String firstname;

    private String role;

    public User2(String username, String password, String lastname, String firstname, String role) {

        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;

    }

    public User2() {

    }

}
