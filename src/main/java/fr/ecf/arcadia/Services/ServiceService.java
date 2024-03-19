package fr.ecf.arcadia.Services;

import java.util.List;

import fr.ecf.arcadia.pojo.Service;

public interface ServiceService {

        public List<Service> getAllServices();
        public Service addService(Service newService);
        public Service getService(Long id);
        public Service updateService(Service newService, Long id);
        public void deleteService(Long id);

}
