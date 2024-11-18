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

import fr.ecf.arcadia.Services.FoodAnimalService;
import fr.ecf.arcadia.pojo.FoodAnimal;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.FOODANIMAL)
public class FoodAnimalController {

    @Autowired
    private FoodAnimalService foodAnimalService;

    @GetMapping
    @PreAuthorize("hasAuthority('EMPLOYEE') || hasAuthority('VETERINARY') || hasAuthority('ADMIN')")
    public List<FoodAnimal> getAllFoodsAnimal(Long id_animal) {
        return foodAnimalService.getAllFoodsAnimal(id_animal);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public FoodAnimal newFoodAnimal(@RequestBody FoodAnimal foodAnimal) {
        return foodAnimalService.addFoodAnimal(foodAnimal);
    }
    
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping("/{id}")
    public FoodAnimal one(@PathVariable Long id) {      
        return foodAnimalService.getFoodAnimal(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public FoodAnimal updateFoodAnimal(@RequestBody FoodAnimal foodAnimal, @PathVariable Long id) {        
        return foodAnimalService.updateFoodAnimal(foodAnimal, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void deleteFoodAnimal(@PathVariable Long id) {
        foodAnimalService.deleteFoodAnimal(id);
    }
    
}
