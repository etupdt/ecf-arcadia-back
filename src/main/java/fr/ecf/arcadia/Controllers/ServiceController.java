package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.ecf.arcadia.pojo.Service;
import fr.ecf.arcadia.repositories.ServiceRepository;

@RestController
public class ServiceController {

    private final ServiceRepository repository;

    ServiceController(ServiceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/services")
    List<Service> all() {
        return repository.findAll();
    }

    @PostMapping("/services")
    Service newService(@RequestBody Service newService) {
        return repository.save(newService);
    }
    
    @GetMapping("/services/{id}")
    Service one(@PathVariable Long id) {
        
        return repository.findById(id)
            .orElseThrow(() -> new ServiceNotFoundException(id));
    }

    @PutMapping("/services/{id}")
    Service replaceService(@RequestBody Service newService, @PathVariable Long id) {
        
        return repository.findById(id)
        .map(service -> {
            service.setName(newService.getName());
            service.setDescription(newService.getDescription());
            return repository.save(service);
        })
        .orElseGet(() -> {
            newService.setId(id);
            return repository.save(newService);
        });
    }

    @DeleteMapping("/services/{id}")
    void deleteService(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
}
