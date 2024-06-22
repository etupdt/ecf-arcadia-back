package fr.ecf.arcadia.Services;

import java.util.List;

import fr.ecf.arcadia.pojo.Food;

public interface FoodService {

        public List<Food> getAllFoods();
        public Food addFood(Food newFood);
        public Food getFood(Long id);
        public Food updateFood(Food newFood, Long id);
        public void deleteFood(Long id);

}
