package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ecf.arcadia.pojo.VeterinaryReport;
import fr.ecf.arcadia.repositories.VeterinaryReportRepository;

@Service
public class VeterinaryReportServiceImpl implements VeterinaryReportService {

    @Autowired
    private VeterinaryReportRepository repository;

    public VeterinaryReportServiceImpl () {
    }

    @Override
    public List<VeterinaryReport> getAllVeterinaryReports() {
        return repository.findAll();
    }

    @Override
    public VeterinaryReport addVeterinaryReport(VeterinaryReport veterinaryReport) {
        return repository.save(veterinaryReport);
    }
    
    @Override
    public VeterinaryReport getVeterinaryReport(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new VeterinaryReportNotFoundException(id));
    }

    @Override
    public VeterinaryReport updateVeterinaryReport(VeterinaryReport newVeterinaryReport, Long id) {
        
        return repository.findById(id)
        .map(veterinaryReport -> {
            veterinaryReport.setDate(newVeterinaryReport.getDate());
            veterinaryReport.setDetail(newVeterinaryReport.getDetail());
            veterinaryReport.setGramage(newVeterinaryReport.getGramage());
            veterinaryReport.setAnimal(newVeterinaryReport.getAnimal());
            veterinaryReport.setFood(newVeterinaryReport.getFood());
            return repository.save(veterinaryReport);
        })
        .orElseGet(() -> {
            newVeterinaryReport.setId(id);
            return repository.save(newVeterinaryReport);
        });
    }

    @Override
    public void deleteVeterinaryReport(Long id) {
        repository.deleteById(id);
    }
    
}
