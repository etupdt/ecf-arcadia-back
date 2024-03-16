package fr.ecf.arcadia.Services;

public class HoursNotFoundException  extends RuntimeException{

    HoursNotFoundException(Long id) {
        super("Could not find hours " + id);
    }
    
}
