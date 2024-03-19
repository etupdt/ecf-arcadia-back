package fr.ecf.arcadia.Services;

import java.util.List;

import fr.ecf.arcadia.pojo.Animal;

public interface AnimalService {

        public List<Animal> getAllAnimals();
        public Animal addAnimal(Animal newAnimal);
        public Animal getAnimal(Long id);
        public Animal updateAnimal(Animal newAnimal, Long id);
        public void deleteAnimal(Long id);

}
