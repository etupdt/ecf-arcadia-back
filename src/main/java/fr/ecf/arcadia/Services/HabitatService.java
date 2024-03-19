package fr.ecf.arcadia.Services;

import java.util.List;

import fr.ecf.arcadia.pojo.Habitat;

public interface HabitatService {

        public List<Habitat> getAllHabitats();
        public Habitat addHabitat(Habitat newHabitat);
        public Habitat getHabitat(Long id);
        public Habitat updateHabitat(Habitat newHabitat, Long id);
        public void deleteHabitat(Long id);

}
