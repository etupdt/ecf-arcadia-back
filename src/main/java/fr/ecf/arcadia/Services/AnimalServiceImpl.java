// newman run .\Mocks\Step1-runArcadia.json -e .\mocks\ecf-arcadia-back_env.json -d .\mocks\habitats.json --export-globals ./mocks/var.txt --verbose

package fr.ecf.arcadia.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.ecf.arcadia.pojo.Animal;
import fr.ecf.arcadia.repositories.AnimalRepository;
import fr.ecf.arcadia.repositories.AnimalStatisticRepository;

@Service
public class AnimalServiceImpl implements AnimalService {

    private Gson gson = new GsonBuilder()
    .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
    .create();

    @Autowired
    private AppFileService fileService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AnimalRepository repository;

    @Autowired
    private AnimalStatisticRepository statisticRepository;

    public AnimalServiceImpl () {
    }

    @Override
    public List<Animal> getAllAnimals() {
        return repository.findAll();
    }

    @Override
    public Animal addAnimal(MultipartFile[] files, String item) {

        Animal animal = this.gson.fromJson(item, Animal.class); 
        
        if (!(null == files)) {
            for (MultipartFile file : files) {
                this.fileService.saveUploadedFile(file, file.getOriginalFilename());
            }    
        }

        return repository.save(animal);

    }
    
    @Override
    public void setAnimalStatistic(Animal animal) {
        this.statisticRepository.findAndIncrementStatisticsByFirstname(animal.getFirstname());
    }

    @Override
    public Animal getAnimal(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new AnimalNotFoundException(id));
    }

    @Override
    public Animal updateAnimal(MultipartFile[] files, String item, Long id) {
        
        Animal newAnimal = this.gson.fromJson(item, Animal.class); 

        if (!(null == files)) {
            for (MultipartFile file : files) {
                this.fileService.saveUploadedFile(file, file.getOriginalFilename());
            }    
        }

        return repository.findById(id)
        .map(animal -> {
            this.imageService.deleteOldImagesFile(animal.getImages(), newAnimal.getImages());
            animal.setFirstname(newAnimal.getFirstname());
            animal.setHealth(newAnimal.getHealth());
            animal.setDescription(newAnimal.getDescription());
            animal.setRace(newAnimal.getRace());
            animal.setHabitat(newAnimal.getHabitat());
            animal.setImages(newAnimal.getImages());
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
