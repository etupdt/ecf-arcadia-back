package fr.ecf.arcadia.pojo;

import java.util.ArrayList;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

    // @ManyToMany(mappedBy = "images")
    // private Set<Habitat> habitats;

    public Image(String imageName) {

        this.imageName = imageName;

    }

    public Image() {

    }

}
