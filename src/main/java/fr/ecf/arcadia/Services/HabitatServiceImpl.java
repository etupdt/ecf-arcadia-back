package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ecf.arcadia.pojo.Habitat;
import fr.ecf.arcadia.repositories.HabitatRepository;

@Service
public class HabitatServiceImpl implements HabitatService {

    @Autowired
    private HabitatRepository repository;

    public HabitatServiceImpl () {
    }

    @Override
    public List<Habitat> getAllHabitats() {
        return repository.findAll();
    }

    @Override
    public Habitat addHabitat(Habitat habitat) {
        return repository.save(habitat);
    }
    
    @Override
    public Habitat getHabitat(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new HabitatNotFoundException(id));
    }

    @Override
    public Habitat updateHabitat(Habitat newHabitat, Long id) {
        
        return repository.findById(id)
        .map(habitat -> {
            habitat.setName(newHabitat.getName());
            habitat.setDescription(newHabitat.getDescription());
            habitat.setComment(newHabitat.getComment());
            return repository.save(habitat);
        })
        .orElseGet(() -> {
            newHabitat.setId(id);
            return repository.save(newHabitat);
        });
    }

    @Override
    public void deleteHabitat(Long id) {
        repository.deleteById(id);
    }
    
}
