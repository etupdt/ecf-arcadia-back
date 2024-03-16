package fr.ecf.arcadia.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;
import fr.ecf.arcadia.pojo.composite.CompositeAnimalImage;

@Entity
@Getter
@Setter
@IdClass(CompositeAnimalImage.class)
public class AnimalImage {

    @Id
    private Long animal_id;
    
    @Id
    private Long image_id;
    
}
