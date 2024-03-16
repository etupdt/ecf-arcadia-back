package fr.ecf.arcadia.Services;

public class HabitatNotFoundException  extends RuntimeException{

    HabitatNotFoundException(Long id) {
        super("Could not find habitat " + id);
    }
    
}
