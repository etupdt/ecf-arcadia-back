package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.ecf.arcadia.pojo.Habitat;

public interface HabitatService {
    
    public List<Habitat> getAllHabitats();
    public Habitat addHabitat(MultipartFile[] files, String item);
    public Habitat getHabitat(Long id);
    public Habitat updateHabitat(MultipartFile[] files, String item, Long id);
    public Habitat updateHabitatByVeterinary(MultipartFile[] files, String item, Long id);
    public void deleteHabitat(Long id);

}
