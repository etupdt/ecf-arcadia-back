package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<VeterinaryReport> getAllVeterinaryReports() {
        return veterinaryReportService.getAllVeterinaryReports();
    }

    @PostMapping
    public VeterinaryReport newVeterinaryReport(@RequestBody VeterinaryReport veterinaryReport) {
        return veterinaryReportService.addVeterinaryReport(veterinaryReport);
    }
    
    @GetMapping("/{id}")
    public VeterinaryReport one(@PathVariable Long id) {      
        return veterinaryReportService.getVeterinaryReport(id);
    }

    @PutMapping("/{id}")
    public VeterinaryReport updateVeterinaryReport(@RequestBody VeterinaryReport veterinaryReport, @PathVariable Long id) {        
        return veterinaryReportService.updateVeterinaryReport(veterinaryReport, id);
    }

    @DeleteMapping("/{id}")
    public void deleteVeterinaryReport(@PathVariable Long id) {
        veterinaryReportService.deleteVeterinaryReport(id);
    }
    
}
