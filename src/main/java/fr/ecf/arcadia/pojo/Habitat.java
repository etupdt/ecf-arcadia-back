package fr.ecf.arcadia.pojo;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    @JsonIgnoreProperties(value = {"habitat"}, allowSetters = true)
    @OneToMany(mappedBy = "habitat", cascade = CascadeType.ALL)
    private Set<Animal> animals;


    public Habitat(String name, String description, String comment) {

        this.name = name;
        this.description = description;
        this.comment = comment;

    }

    public Habitat() {

    }

}
