package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.ecf.arcadia.Services.FoodAnimalService;
import fr.ecf.arcadia.pojo.FoodAnimal;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.FOODANIMAL)
public class FoodAnimalController {

    private static final Logger logger = LoggerFactory.getLogger(FoodAnimalController.class);

    @Autowired
    private FoodAnimalService foodAnimalService;

    @GetMapping
    public List<FoodAnimalController> getAllFoodAnimals() {
        return foodAnimalService.getAllFoodAnimals();
    }

    @PostMapping
    public FoodAnimalController newFoodAnimal(@RequestParam("file") MultipartFile file, @RequestParam String foodAnimalInText) {
        return foodAnimalService.addFoodAnimal(file, foodAnimalInText);
    }
    
    @GetMapping("/{id}")
    public FoodAnimalController one(@PathVariable Long id) {      
        return foodAnimalService.getFoodAnimal(id);
    }

    @PutMapping("/{id}")
    public FoodAnimalController updateFoodAnimal(@RequestBody FoodAnimalController foodAnimal, @PathVariable Long id) {        
        return foodAnimalService.updateFoodAnimal(foodAnimal, id);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodAnimal(@PathVariable Long id) {
        foodAnimalService.deleteFoodAnimal(id);
    }
    
}
