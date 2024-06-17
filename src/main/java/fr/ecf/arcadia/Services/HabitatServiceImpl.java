package fr.ecf.arcadia.Services;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.ecf.arcadia.pojo.Habitat;
import fr.ecf.arcadia.repositories.HabitatRepository;

@Service
public class HabitatServiceImpl implements HabitatService {

    private static final Logger logger = LoggerFactory.getLogger(HabitatService.class);

    private Gson gson = new GsonBuilder()
    .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
    .create();

    @Autowired
    private AppFileService fileService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private HabitatRepository repository;

    public HabitatServiceImpl () {
    }

    @Override
    public List<Habitat> getAllHabitats() {
        return repository.findAll();
    }

    @Override
    public Habitat addHabitat(MultipartFile[] files, String item) {

        logger.debug("=============> executing service addHabitat ");

        Habitat habitat = this.gson.fromJson(item, Habitat.class); 
        
        if (!(null == files)) {
            for (MultipartFile file : files) {
                this.fileService.saveUploadedFile(file, file.getOriginalFilename());
            }    
        }

        return repository.save(habitat);

    }
    
    @Override
    public Habitat getHabitat(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new HabitatNotFoundException(id));
    }

    @Override
    public Habitat updateHabitat(MultipartFile[] files, String item, Long id) {
        
        Habitat newHabitat = this.gson.fromJson(item, Habitat.class); 

        if (!(null == files)) {
            for (MultipartFile file : files) {
                this.fileService.saveUploadedFile(file, file.getOriginalFilename());
            }    
        }

        return repository.findById(id)
        .map(habitat -> {
            this.imageService.deleteOldImagesFile(habitat.getImages(), newHabitat.getImages());
            habitat.setName(newHabitat.getName());
            habitat.setDescription(newHabitat.getDescription());
            habitat.setComment(newHabitat.getComment());
            habitat.setImages(newHabitat.getImages());
            return repository.save(habitat);
        })
        .orElseGet(() -> {
            newHabitat.setId(id);
            return repository.save(newHabitat);
        });
        
    }

    @Override
    public void deleteHabitat(Long id) {
        repository.deleteById(id);
    }
    
}
