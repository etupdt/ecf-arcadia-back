package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ecf.arcadia.Services.BreedService;
import fr.ecf.arcadia.pojo.Breed;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.BREED)
public class BreedController {

    @Autowired
    private BreedService breedService;

    @GetMapping
    public List<Breed> getAllBreeds() {
        return breedService.getAllBreeds();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Breed newBreed(@RequestBody Breed breed) {
        return breedService.addBreed(breed);
    }
    
    @GetMapping("/{id}")
    public Breed one(@PathVariable Long id) {      
        return breedService.getBreed(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Breed uodateBreed(@RequestBody Breed breed, @PathVariable Long id) {        
        return breedService.updateBreed(breed, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteBreed(@PathVariable Long id) {
        breedService.deleteBreed(id);
    }
    
}
