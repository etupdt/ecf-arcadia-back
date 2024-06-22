package fr.ecf.arcadia.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Habitat {
    
    private static final Logger logger = LoggerFactory.getLogger(Image.class);
    
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

    @PreUpdate
    public void logImagePreUpdateAttempt() {
        logger.info("Attempting to preupdate habitat: " + name);
        for (Image image : images) {
            logger.info(image.getImageName());
        }
    }
    
    @PostUpdate
    public void logImagePostUpdateAttempt() {
        logger.info("Attempting to postupdate habitat: " + name);
        for (Image image : images) {
            logger.info(image.getImageName());
        }
    }
    
    @PostRemove
    public void logUserRemoval() {
        logger.info("Deleted habitat: " + name);
    }

    @PostLoad
    public void logNewUserAdded() {
        logger.info("post load habitat '" + name + "' with ID: " + id);
    }
        

}
