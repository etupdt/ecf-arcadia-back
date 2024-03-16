package fr.ecf.arcadia.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String comment;

    public Habitat(String name, String description, String comment) {

        this.name = name;
        this.description = description;
        this.comment = comment;

    }

    public Habitat() {

    }

}
