package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.ecf.arcadia.Services.HabitatService;
import fr.ecf.arcadia.pojo.Habitat;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.HABITAT)
public class HabitatController {

    @Autowired
    private HabitatService habitatService;

    @GetMapping
    public List<Habitat> getAllHabitats() {
        return habitatService.getAllHabitats();
    }

    @PostMapping
    public Habitat newHabitat(@RequestParam("file") MultipartFile file, @RequestParam String habitatInText) {
        return habitatService.addHabitat(file, habitatInText);
    }
    
    // @PostMapping
    // public Habitat newHabitat(@RequestBody Habitat habitat) {
    //     return habitatService.addHabitat(habitat);
    // }
    
    @GetMapping("/{id}")
    public Habitat one(@PathVariable Long id) {      
        return habitatService.getHabitat(id);
    }

    @PutMapping("/{id}")
    public Habitat updateHabitat(@RequestBody Habitat habitat, @PathVariable Long id) {        
        return habitatService.updateHabitat(habitat, id);
    }

    @DeleteMapping("/{id}")
    public void deleteHabitat(@PathVariable Long id) {
        habitatService.deleteHabitat(id);
    }
    
}
