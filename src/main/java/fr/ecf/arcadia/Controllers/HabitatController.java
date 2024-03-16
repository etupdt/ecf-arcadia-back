package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.ecf.arcadia.pojo.Habitat;
import fr.ecf.arcadia.repositories.HabitatRepository;

@RestController
public class HabitatController {

    private final HabitatRepository repository;

    HabitatController(HabitatRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/habitats")
    List<Habitat> all() {
        return repository.findAll();
    }

    @PostMapping("/habitats")
    Habitat newHabitat(@RequestBody Habitat newHabitat) {
        return repository.save(newHabitat);
    }
    
    @GetMapping("/habitats/{id}")
    Habitat one(@PathVariable Long id) {
        
        return repository.findById(id)
            .orElseThrow(() -> new HabitatNotFoundException(id));
    }

    @PutMapping("/habitats/{id}")
    Habitat replaceHabitat(@RequestBody Habitat newHabitat, @PathVariable Long id) {
        
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

    @DeleteMapping("/habitats/{id}")
    void deleteHabitat(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
}
