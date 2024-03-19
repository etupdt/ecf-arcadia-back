package fr.ecf.arcadia.pojo.composite;

import java.io.Serializable;

public class CompositeAnimalImage implements Serializable {

    private Long animal_id;

    private Long image_id;

    public CompositeAnimalImage(Long animal_id, Long image_id) {
        this.animal_id = animal_id;
        this.image_id =  image_id;
    }

}
