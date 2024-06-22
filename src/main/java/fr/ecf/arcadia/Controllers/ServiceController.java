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

import fr.ecf.arcadia.Services.ServiceService;
import fr.ecf.arcadia.pojo.Service;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.SERVICE)
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @PostMapping
    public Service newService(@RequestBody Service service) {
        return serviceService.addService(service);
    }
    
    @GetMapping("/{id}")
    public Service one(@PathVariable Long id) {      
        return serviceService.getService(id);
    }

    @PutMapping("/{id}")
    public Service uodateService(@RequestBody Service service, @PathVariable Long id) {        
        return serviceService.updateService(service, id);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
    }
    
}
