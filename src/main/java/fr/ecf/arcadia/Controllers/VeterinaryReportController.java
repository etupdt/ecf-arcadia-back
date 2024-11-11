package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ecf.arcadia.Services.VeterinaryReportService;
import fr.ecf.arcadia.pojo.VeterinaryReport;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.VETERINARYREPORT)
public class VeterinaryReportController {

    @Autowired
    private VeterinaryReportService veterinaryReportService;

    @GetMapping
    @PreAuthorize("hasAuthority('VETERINARY') || hasAuthority('ADMIN')")
    public List<VeterinaryReport> getAllVeterinaryReports() {
        return veterinaryReportService.getAllVeterinaryReports();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('VETERINARY')")
    public VeterinaryReport newVeterinaryReport(@RequestBody VeterinaryReport veterinaryReport) {
        return veterinaryReportService.addVeterinaryReport(veterinaryReport);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VETERINARY')")
    public VeterinaryReport one(@PathVariable Long id) {      
        return veterinaryReportService.getVeterinaryReport(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('VETERINARY')")
    public VeterinaryReport updateVeterinaryReport(@RequestBody VeterinaryReport veterinaryReport, @PathVariable Long id) {        
        return veterinaryReportService.updateVeterinaryReport(veterinaryReport, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('VETERINARY')")
    public void deleteVeterinaryReport(@PathVariable Long id) {
        veterinaryReportService.deleteVeterinaryReport(id);
    }
    
}
