package fr.ecf.arcadia.pojo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String health;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_race")
    private Race race;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_habitat")
    private Habitat habitat;

    public Animal(String firstname, String health) {

        this.firstname = firstname;
        this.health = health;

    }

    public Animal() {

    }

}
