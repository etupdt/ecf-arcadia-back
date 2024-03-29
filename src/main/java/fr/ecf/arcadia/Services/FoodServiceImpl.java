package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ecf.arcadia.pojo.Food;
import fr.ecf.arcadia.repositories.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository repository;

    public FoodServiceImpl () {
    }

    @Override
    public List<Food> getAllFoods() {
        return repository.findAll();
    }

    @Override
    public Food addFood(Food food) {
        return repository.save(food);
    }
    
    @Override
    public Food getFood(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new FoodNotFoundException(id));
    }

    @Override
    public Food updateFood(Food newFood, Long id) {
        
        return repository.findById(id)
        .map(food -> {
            food.setName(newFood.getName());
            return repository.save(food);
        })
        .orElseGet(() -> {
            newFood.setId(id);
            return repository.save(newFood);
        });
    }

    @Override
    public void deleteFood(Long id) {
        repository.deleteById(id);
    }
    
}
