package fr.ecf.arcadia.Services;

public class VeterinaryReportNotFoundException  extends RuntimeException{

    VeterinaryReportNotFoundException(Long id) {
        super("Could not find veterinaryReport " + id);
    }
    
}
