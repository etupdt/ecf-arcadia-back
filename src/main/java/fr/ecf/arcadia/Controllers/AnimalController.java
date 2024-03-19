package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ecf.arcadia.Services.AnimalService;
import fr.ecf.arcadia.pojo.Animal;

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
    public Animal newAnimal(@RequestBody Animal animal) {
        return animalService.addAnimal(animal);
    }
    
    @GetMapping("/{id}")
    public Animal one(@PathVariable Long id) {      
        return animalService.getAnimal(id);
    }

    @PutMapping("/{id}")
    public Animal uodateAnimal(@RequestBody Animal animal, @PathVariable Long id) {        
        return animalService.updateAnimal(animal, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
    }
    
}
