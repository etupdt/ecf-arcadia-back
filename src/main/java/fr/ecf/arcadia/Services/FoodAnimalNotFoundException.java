package fr.ecf.arcadia.Services;

public class FoodAnimalNotFoundException  extends RuntimeException{

    FoodAnimalNotFoundException(Long id) {
        super("Could not find foodAnimal " + id);
    }
    
}
