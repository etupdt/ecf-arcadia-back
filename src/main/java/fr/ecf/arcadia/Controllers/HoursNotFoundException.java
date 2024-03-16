package fr.ecf.arcadia.Controllers;

public class HoursNotFoundException  extends RuntimeException{

    HoursNotFoundException(Long id) {
        super("Could not find hours " + id);
    }
    
}
