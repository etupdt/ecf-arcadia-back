package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ecf.arcadia.repositories.ServiceRepository;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository repository;

    public ServiceServiceImpl () {
    }

    @Override
    public List<fr.ecf.arcadia.pojo.Service> getAllServices() {
        return repository.findAll();
    }

    @Override
    public fr.ecf.arcadia.pojo.Service addService(fr.ecf.arcadia.pojo.Service service) {
        return repository.save(service);
    }
    
    @Override
    public fr.ecf.arcadia.pojo.Service getService(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new ServiceNotFoundException(id));
    }

    @Override
    public fr.ecf.arcadia.pojo.Service updateService(fr.ecf.arcadia.pojo.Service newService, Long id) {
        
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

    @Override
    public void deleteService(Long id) {
        repository.deleteById(id);
    }
    
}
