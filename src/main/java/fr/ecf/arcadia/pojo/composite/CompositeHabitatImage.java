package fr.ecf.arcadia.pojo.composite;

import java.io.Serializable;

public class CompositeHabitatImage implements Serializable {

    private Long habitat_id;

    private Long image_id;

    public CompositeHabitatImage(Long habitat_id, Long image_id) {
        this.habitat_id = habitat_id;
        this.image_id =  image_id;
    }

}
