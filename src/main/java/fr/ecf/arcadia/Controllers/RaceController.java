package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.ecf.arcadia.pojo.Race;
import fr.ecf.arcadia.repositories.RaceRepository;

@RestController
public class RaceController {

    private final RaceRepository repository;

    RaceController(RaceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/races")
    List<Race> all() {
        return repository.findAll();
    }

    @PostMapping("/races")
    Race newRace(@RequestBody Race newRace) {
        return repository.save(newRace);
    }
    
    @GetMapping("/races/{id}")
    Race one(@PathVariable Long id) {
        
        return repository.findById(id)
            .orElseThrow(() -> new RaceNotFoundException(id));
    }

    @PutMapping("/races/{id}")
    Race replaceRace(@RequestBody Race newRace, @PathVariable Long id) {
        
        return repository.findById(id)
        .map(race -> {
            race.setLabel(newRace.getLabel());
            return repository.save(race);
        })
        .orElseGet(() -> {
            newRace.setId(id);
            return repository.save(newRace);
        });
    }

    @DeleteMapping("/races/{id}")
    void deleteRace(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
}
