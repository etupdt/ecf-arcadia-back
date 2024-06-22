package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ecf.arcadia.pojo.Race;
import fr.ecf.arcadia.repositories.RaceRepository;

@Service
public class RaceServiceImpl implements RaceService {

    @Autowired
    private RaceRepository repository;

    public RaceServiceImpl () {
    }

    @Override
    public List<Race> getAllRaces() {
        return repository.findAll();
    }

    @Override
    public Race addRace(Race animal) {
        return repository.save(animal);
    }
    
    @Override
    public Race getRace(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new RaceNotFoundException(id));
    }

    @Override
    public Race updateRace(Race newRace, Long id) {
        
        return repository.findById(id)
        .map(animal -> {
            animal.setLabel(newRace.getLabel());
            return repository.save(animal);
        })
        .orElseGet(() -> {
            newRace.setId(id);
            return repository.save(newRace);
        });
    }

    @Override
    public void deleteRace(Long id) {
        repository.deleteById(id);
    }
    
}
