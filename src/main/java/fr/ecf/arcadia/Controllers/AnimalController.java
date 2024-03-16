package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.ecf.arcadia.pojo.Animal;
import fr.ecf.arcadia.repositories.AnimalRepository;

@RestController
public class AnimalController {

    private final AnimalRepository repository;

    AnimalController(AnimalRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/animals")
    List<Animal> all() {
        return repository.findAll();
    }

    @PostMapping("/animals")
    Animal newAnimal(@RequestBody Animal newAnimal) {
        return repository.save(newAnimal);
    }
    
    @GetMapping("/animals/{id}")
    Animal one(@PathVariable Long id) {
        
        return repository.findById(id)
            .orElseThrow(() -> new AnimalNotFoundException(id));
    }

    @PutMapping("/animals/{id}")
    Animal replaceAnimal(@RequestBody Animal newAnimal, @PathVariable Long id) {
        
        return repository.findById(id)
        .map(animal -> {
            animal.setFirstname(newAnimal.getFirstname());
            animal.setHealth(newAnimal.getHealth());
            animal.setRace(newAnimal.getRace());
            animal.setHabitat(newAnimal.getHabitat());
            return repository.save(animal);
        })
        .orElseGet(() -> {
            newAnimal.setId(id);
            return repository.save(newAnimal);
        });
    }

    @DeleteMapping("/animals/{id}")
    void deleteAnimal(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
}
