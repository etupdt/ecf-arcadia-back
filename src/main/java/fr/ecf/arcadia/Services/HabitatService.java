package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.ecf.arcadia.pojo.Habitat;

public interface HabitatService {
    
    public List<Habitat> getAllHabitats();
    public Habitat addHabitat(MultipartFile file, String habitatInText);
    public Habitat getHabitat(Long id);
    public Habitat updateHabitat(Habitat newHabitat, Long id);
    public void deleteHabitat(Long id);

}
