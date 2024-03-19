package fr.ecf.arcadia.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;
import fr.ecf.arcadia.pojo.composite.CompositeHabitatImage;

@Entity
@Getter
@Setter
@IdClass(CompositeHabitatImage.class)
public class HabitatImage {

    @Id
    private Long habitat_id;
    
    @Id
    private Long image_id;
    
}
