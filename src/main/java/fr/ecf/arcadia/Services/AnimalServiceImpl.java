package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ecf.arcadia.pojo.Animal;
import fr.ecf.arcadia.repositories.AnimalRepository;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository repository;

    public AnimalServiceImpl () {
    }

    @Override
    public List<Animal> getAllAnimals() {
        return repository.findAll();
    }

    @Override
    public Animal addAnimal(Animal animal) {
        return repository.save(animal);
    }
    
    @Override
    public Animal getAnimal(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new AnimalNotFoundException(id));
    }

    @Override
    public Animal updateAnimal(Animal newAnimal, Long id) {
        
        return repository.findById(id)
        .map(animal -> {
            animal.setFirstname(newAnimal.getFirstname());
            animal.setHealth(newAnimal.getHealth());
            animal.setRace(newAnimal.getRace());
            animal.setHabitat(newAnimal.getHabitat());
            return repository.save(animal);
        })
        .orElseGet(() -> {
            newAnimal.setId(id);
            return repository.save(newAnimal);
        });
    }

    @Override
    public void deleteAnimal(Long id) {
        repository.deleteById(id);
    }
    
}
