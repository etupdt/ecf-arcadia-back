package fr.ecf.arcadia.pojo;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_breed")
    private Breed breed;

    @JsonIgnoreProperties(value = {"animals"}, allowSetters = true)
    @ManyToOne
    @JoinColumn(name = "id_habitat")
    private Habitat habitat;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "animal_image",
            joinColumns = {@JoinColumn(name = "animal_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")}
    )
    private List<Image> images;

    @JsonIgnoreProperties(value = {"animal"}, allowSetters = true)
    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    private Set<FoodAnimal> foodAnimals;

    @JsonIgnoreProperties(value = {"animal"}, allowSetters = true)
    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    private Set<VeterinaryReport> veterinaryReports;

    public Animal(String firstname, String health, List<Image> images, String description) {

        this.firstname = firstname;
        this.health = health;
        this.images = images;
        this.description = description;

    }

    public Animal() {

    }

}
