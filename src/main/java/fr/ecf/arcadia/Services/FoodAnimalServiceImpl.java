package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ecf.arcadia.pojo.FoodAnimal;
import fr.ecf.arcadia.repositories.FoodAnimalRepository;

@Service
public class FoodAnimalServiceImpl implements FoodAnimalService {

    @Autowired
    private FoodAnimalRepository repository;

    public FoodAnimalServiceImpl () {
    }

    @Override
    public List<FoodAnimal> getAllFoodsAnimal(Long animalId) {
        return repository.findAllByAnimalId(animalId);
    }

    @Override
    public FoodAnimal addFoodAnimal(FoodAnimal foodAnimal) {

        return repository.save(foodAnimal);

    }
    
    @Override
    public FoodAnimal getFoodAnimal(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new FoodAnimalNotFoundException(id));
    }

    @Override
    public FoodAnimal updateFoodAnimal(FoodAnimal newFoodAnimal, Long id) {
        
        return repository.findById(id)
        .map(foodAnimal -> {
            foodAnimal.setGramage(newFoodAnimal.getGramage());
            foodAnimal.setDateFood(newFoodAnimal.getDateFood());
            foodAnimal.setFood(newFoodAnimal.getFood());
            foodAnimal.setAnimal(newFoodAnimal.getAnimal());
            return repository.save(foodAnimal);
        })
        .orElseGet(() -> {
            newFoodAnimal.setId(id);
            return repository.save(newFoodAnimal);
        });
    }

    @Override
    public void deleteFoodAnimal(Long id) {
        repository.deleteById(id);
    }
    
}
