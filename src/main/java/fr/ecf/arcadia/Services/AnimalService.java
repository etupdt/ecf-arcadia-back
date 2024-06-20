package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.ecf.arcadia.pojo.Animal;
import fr.ecf.arcadia.pojo.AnimalStatistic;

public interface AnimalService {

    public List<Animal> getAllAnimals();
    public Animal addAnimal(MultipartFile[] files, String item);
    public void setAnimalStatistic(AnimalStatistic animalStatistic);
    public List<AnimalStatistic> getAnimalsStatistics();
    public Animal getAnimal(Long id);
    public Animal updateAnimal(MultipartFile[] files, String item, Long id);
    public void deleteAnimal(Long id);

}
