package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.ecf.arcadia.pojo.Animal;
import fr.ecf.arcadia.pojo.Habitat;

public interface AnimalService {

        public List<Animal> getAllAnimals();
        public Animal addAnimal(MultipartFile file, String animalInText);
        public Animal getAnimal(Long id);
        public Animal updateAnimal(Animal newAnimal, Long id);
        public void deleteAnimal(Long id);

}
