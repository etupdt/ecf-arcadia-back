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

import fr.ecf.arcadia.Services.RaceService;
import fr.ecf.arcadia.pojo.Race;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.RACE)
public class RaceController {

    @Autowired
    private RaceService raceService;

    @GetMapping
    public List<Race> getAllRaces() {
        return raceService.getAllRaces();
    }

    @PostMapping
    public Race newRace(@RequestBody Race race) {
        return raceService.addRace(race);
    }
    
    @GetMapping("/{id}")
    public Race one(@PathVariable Long id) {      
        return raceService.getRace(id);
    }

    @PutMapping("/{id}")
    public Race uodateRace(@RequestBody Race race, @PathVariable Long id) {        
        return raceService.updateRace(race, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRace(@PathVariable Long id) {
        raceService.deleteRace(id);
    }
    
}
