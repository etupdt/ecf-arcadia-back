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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "habitat_image",
        joinColumns = {@JoinColumn(name = "habitat_id")},
        inverseJoinColumns = {@JoinColumn(name = "image_id")}
    )
    private List<Image> images;

    @JsonIgnoreProperties(value = {"habitat"}, allowSetters = true)
    @OneToMany(mappedBy = "habitat", cascade = CascadeType.REMOVE)
    private Set<Animal> animals;

    public Habitat(String name, String description, String comment, List<Image> images) {

        this.name = name;
        this.description = description;
        this.comment = comment;
        this.images = images;

    }

    public Habitat() {

    }

}
