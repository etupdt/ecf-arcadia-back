package fr.ecf.arcadia.Controllers;

public class RaceNotFoundException  extends RuntimeException{

    RaceNotFoundException(Long id) {
        super("Could not find race " + id);
    }
    
}
