package fr.ecf.arcadia.Services;

import java.util.List;

import fr.ecf.arcadia.pojo.FoodAnimal;

public interface FoodAnimalService {

    public List<FoodAnimal> getAllFoodsAnimal(Long id_animal);
    public FoodAnimal addFoodAnimal(FoodAnimal foodAnimal);
    public FoodAnimal getFoodAnimal(Long id);
    public FoodAnimal updateFoodAnimal(FoodAnimal newFoodAnimal, Long id);
    public void deleteFoodAnimal(Long id);

}
