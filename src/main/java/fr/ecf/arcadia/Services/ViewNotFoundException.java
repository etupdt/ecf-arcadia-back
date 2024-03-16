package fr.ecf.arcadia.Services;

public class ViewNotFoundException  extends RuntimeException{

    ViewNotFoundException(Long id) {
        super("Could not find view " + id);
    }
    
}
