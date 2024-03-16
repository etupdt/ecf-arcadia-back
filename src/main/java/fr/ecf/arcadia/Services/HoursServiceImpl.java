package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ecf.arcadia.pojo.Hours;
import fr.ecf.arcadia.repositories.HoursRepository;

@Service
public class HoursServiceImpl implements HoursService {

    @Autowired
    private HoursRepository repository;

    public HoursServiceImpl () {
    }

    @Override
    public List<Hours> getAllHourss() {
        return repository.findAll();
    }

    @Override
    public Hours addHours(Hours hours) {
        return repository.save(hours);
    }
    
    @Override
    public Hours getHours(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new HoursNotFoundException(id));
    }

    @Override
    public Hours updateHours(Hours newHours, Long id) {
        
        return repository.findById(id)
        .map(hours -> {
            hours.setMonday(newHours.getMonday());
            hours.setTuesday(newHours.getTuesday());
            hours.setWednesday(newHours.getWednesday());
            hours.setThursday(newHours.getThursday());
            hours.setFriday(newHours.getFriday());
            hours.setSaturday(newHours.getSaturday());
            hours.setSunday(newHours.getSunday());
            return repository.save(hours);
        })
        .orElseGet(() -> {
            newHours.setId(id);
            return repository.save(newHours);
        });
    }

    @Override
    public void deleteHours(Long id) {
        repository.deleteById(id);
    }
    
}
