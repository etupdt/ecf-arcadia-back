package fr.ecf.arcadia.Services;

public class AnimalNotFoundException  extends RuntimeException{

    AnimalNotFoundException(Long id) {
        super("Could not find animal " + id);
    }
    
}
