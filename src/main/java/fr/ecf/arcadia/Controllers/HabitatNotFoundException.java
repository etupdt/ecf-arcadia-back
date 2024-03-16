package fr.ecf.arcadia.Controllers;

public class HabitatNotFoundException  extends RuntimeException{

    HabitatNotFoundException(Long id) {
        super("Could not find habitat " + id);
    }
    
}
