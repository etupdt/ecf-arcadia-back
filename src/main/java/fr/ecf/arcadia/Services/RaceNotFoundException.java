package fr.ecf.arcadia.Services;

public class RaceNotFoundException  extends RuntimeException{

    RaceNotFoundException(Long id) {
        super("Could not find race " + id);
    }
    
}
