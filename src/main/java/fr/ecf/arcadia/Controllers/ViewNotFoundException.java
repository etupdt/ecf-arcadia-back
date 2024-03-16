package fr.ecf.arcadia.Controllers;

public class ViewNotFoundException  extends RuntimeException{

    ViewNotFoundException(Long id) {
        super("Could not find view " + id);
    }
    
}
