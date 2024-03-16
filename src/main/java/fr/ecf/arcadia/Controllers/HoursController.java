package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.ecf.arcadia.pojo.Hours;
import fr.ecf.arcadia.repositories.HoursRepository;

@RestController
public class HoursController {

    private final HoursRepository repository;

    HoursController(HoursRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/hours")
    List<Hours> all() {
        return repository.findAll();
    }

    @PostMapping("/hours")
    Hours newHours(@RequestBody Hours newHours) {
        return repository.save(newHours);
    }
    
    @GetMapping("/hours/{id}")
    Hours one(@PathVariable Long id) {
        
        return repository.findById(id)
            .orElseThrow(() -> new HoursNotFoundException(id));
    }

    @PutMapping("/hours/{id}")
    Hours replaceHours(@RequestBody Hours newHours, @PathVariable Long id) {
        
        return repository.findById(id)
        .map(hours -> {
            hours.setMonday(newHours.getMonday());
            hours.setTuesday(newHours.getTuesday());
            hours.setWednesday(newHours.getWednesday());
            hours.setThursday(newHours.getThursday());
            hours.setFriday(newHours.getFriday());
            hours.setSaturday(newHours.getSaturday());
            hours.setSunday(newHours.getSunday());
            return repository.save(hours);
        })
        .orElseGet(() -> {
            newHours.setId(id);
            return repository.save(newHours);
        });
    }

    @DeleteMapping("/hours/{id}")
    void deleteHours(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
}
