package fr.ecf.arcadia.Services;

import java.util.List;

import fr.ecf.arcadia.pojo.VeterinaryReport;

public interface VeterinaryReportService {

        public List<VeterinaryReport> getAllVeterinaryReports();
        public VeterinaryReport addVeterinaryReport(VeterinaryReport newVeterinaryReport);
        public VeterinaryReport getVeterinaryReport(Long id);
        public VeterinaryReport updateVeterinaryReport(VeterinaryReport newVeterinaryReport, Long id);
        public void deleteVeterinaryReport(Long id);

}
