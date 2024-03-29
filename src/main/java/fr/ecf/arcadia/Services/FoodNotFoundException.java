package fr.ecf.arcadia.Services;

public class FoodNotFoundException  extends RuntimeException{

    FoodNotFoundException(Long id) {
        super("Could not find food " + id);
    }
    
}
