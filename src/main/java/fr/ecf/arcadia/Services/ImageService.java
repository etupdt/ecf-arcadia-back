package fr.ecf.arcadia.Services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.ecf.arcadia.pojo.Image;

@Service
public class ImageService {

    private static final Logger logger = LoggerFactory.getLogger(HabitatService.class);

    @Autowired
    private AppFileService fileService;

    public ImageService () {
    }

    public void deleteOldImagesFile(List<Image> oldImages, List<Image> newImages) {
        for (Image oldImage : oldImages) {
            if (null == newImages.stream()
            .filter(newImage -> oldImage.getHash().equals(newImage.getHash()))
            .findAny()
            .orElse(null)) {
                this.fileService.deleteFile(oldImage.getImageName());
            }
        }
    }

}
