package fr.ecf.arcadia.Services;

public class ServiceNotFoundException  extends RuntimeException{

    ServiceNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
    
}
