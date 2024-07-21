package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ecf.arcadia.pojo.Breed;
import fr.ecf.arcadia.repositories.BreedRepository;

@Service
public class BreedServiceImpl implements BreedService {

    @Autowired
    private BreedRepository repository;

    public BreedServiceImpl () {
    }

    @Override
    public List<Breed> getAllBreeds() {
        return repository.findAll();
    }

    @Override
    public Breed addBreed(Breed animal) {
        return repository.save(animal);
    }
    
    @Override
    public Breed getBreed(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new BreedNotFoundException(id));
    }

    @Override
    public Breed updateBreed(Breed newBreed, Long id) {
        
        return repository.findById(id)
        .map(animal -> {
            animal.setLabel(newBreed.getLabel());
            return repository.save(animal);
        })
        .orElseGet(() -> {
            newBreed.setId(id);
            return repository.save(newBreed);
        });
    }

    @Override
    public void deleteBreed(Long id) {
        repository.deleteById(id);
    }
    
}
