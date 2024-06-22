package fr.ecf.arcadia.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PreRemove;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image {

    private static final Logger logger = LoggerFactory.getLogger(Image.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

    private String hash;

    public Image(String imageName) {

        this.imageName = imageName;

    }

    public Image() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image that = (Image) o;
        return hash.equals(that.hash);
    }

    
    @PreRemove
    public void logUserRemoval() {
        logger.info("Deleted image: " + imageName);
    }

    @PostLoad
    public void logNewUserAdded() {
        logger.info("post load image '" + imageName + "' with ID: " + id);
    }
        
}
