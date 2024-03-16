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

import fr.ecf.arcadia.Services.HoursService;
import fr.ecf.arcadia.pojo.Hours;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.HOURS)
public class HoursController {

    @Autowired
    private HoursService hoursService;

    @GetMapping
    public List<Hours> getAllHourss() {
        return hoursService.getAllHourss();
    }

    @PostMapping
    public Hours newHours(@RequestBody Hours hours) {
        return hoursService.addHours(hours);
    }
    
    @GetMapping("/{id}")
    public Hours one(@PathVariable Long id) {      
        return hoursService.getHours(id);
    }

    @PutMapping("/{id}")
    public Hours uodateHours(@RequestBody Hours hours, @PathVariable Long id) {        
        return hoursService.updateHours(hours, id);
    }

    @DeleteMapping("/{id}")
    public void deleteHours(@PathVariable Long id) {
        hoursService.deleteHours(id);
    }
    
}
