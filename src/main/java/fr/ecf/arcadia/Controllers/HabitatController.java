package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.ecf.arcadia.Services.HabitatService;
import fr.ecf.arcadia.pojo.Habitat;
import io.micrometer.common.lang.Nullable;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.HABITAT)
public class HabitatController {

    private static final Logger logger = LoggerFactory.getLogger(HabitatController.class);

    @Autowired
    private HabitatService habitatService;

    @GetMapping
    public List<Habitat> getAllHabitats() {
        return habitatService.getAllHabitats();
    }

    @PostMapping
    public Habitat newHabitat(@Nullable@RequestParam("files") MultipartFile[] files, @RequestParam String item) {
        return habitatService.addHabitat(files, item);
    }
    
    @GetMapping("/{id}")
    public Habitat one(@PathVariable Long id) {      
        return habitatService.getHabitat(id);
    }

    @PutMapping("/{id}")
    public Habitat updateHabitat(@Nullable@RequestParam("files") MultipartFile[] files, @RequestParam String item, @PathVariable Long id) {        
        return habitatService.updateHabitat(files, item, id);
    }

    @DeleteMapping("/{id}")
    public void deleteHabitat(@PathVariable Long id) {
        habitatService.deleteHabitat(id);
    }
    
}
