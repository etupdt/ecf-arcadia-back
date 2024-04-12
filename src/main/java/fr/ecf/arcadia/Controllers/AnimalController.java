package fr.ecf.arcadia.Controllers;

import java.util.List;

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

import fr.ecf.arcadia.Services.AnimalService;
import fr.ecf.arcadia.pojo.Animal;
import io.micrometer.common.lang.Nullable;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.ANIMAL)
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @PostMapping
    public Animal newHabitat(@RequestParam("files") MultipartFile[] files, @RequestParam String item) {
        return animalService.addAnimal(files, item);
    }
    
    @GetMapping("/{id}")
    public Animal one(@PathVariable Long id) {      
        return animalService.getAnimal(id);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@Nullable@RequestParam("files") MultipartFile[] files, @RequestParam String item, @PathVariable Long id) {        
        return animalService.updateAnimal(files, item, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
    }
    
}
