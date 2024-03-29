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
import fr.ecf.arcadia.pojo.Image;
import fr.ecf.arcadia.repositories.HabitatRepository;

@Service
public class HabitatServiceImpl implements HabitatService {

    private static final Logger logger = LoggerFactory.getLogger(HabitatService.class);

    @Autowired
    private AppFileService fileService;

    @Autowired
    private HabitatRepository repository;

    public HabitatServiceImpl () {
    }

    @Override
    public List<Habitat> getAllHabitats() {
        return repository.findAll();
    }

    @Override
    public Habitat addHabitat(MultipartFile file, String habitatInText) {

        logger.debug("=============> executing service addHabitat ");

        Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
        .create();
        Habitat habitat = gson.fromJson(habitatInText, Habitat.class); 
        
        for (Image image : habitat.getImages()) {
            image.setImageName(this.fileService.saveUploadedFile(file, "habitat_" + habitat.getName()));
            break;
        }    
        
        return repository.save(habitat);

    }
    
    @Override
    public Habitat getHabitat(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new HabitatNotFoundException(id));
    }

    @Override
    public Habitat updateHabitat(Habitat newHabitat, Long id) {
        
        return repository.findById(id)
        .map(habitat -> {
            habitat.setName(newHabitat.getName());
            habitat.setDescription(newHabitat.getDescription());
            habitat.setComment(newHabitat.getComment());
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
