package fr.ecf.arcadia.Controllers;

public class ServiceNotFoundException  extends RuntimeException{

    ServiceNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
    
}
