package fr.ecf.arcadia.Services;

import java.util.List;

import fr.ecf.arcadia.pojo.Breed;

public interface BreedService {

        public List<Breed> getAllBreeds();
        public Breed addBreed(Breed newBreed);
        public Breed getBreed(Long id);
        public Breed updateBreed(Breed newBreed, Long id);
        public void deleteBreed(Long id);

}
