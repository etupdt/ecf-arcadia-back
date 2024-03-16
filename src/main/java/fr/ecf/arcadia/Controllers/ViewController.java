package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.ecf.arcadia.pojo.View;
import fr.ecf.arcadia.repositories.ViewRepository;

@RestController
public class ViewController {

    private final ViewRepository repository;

    ViewController(ViewRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/views")
    List<View> all() {
        return repository.findAll();
    }

    @PostMapping("/views")
    View newView(@RequestBody View newView) {
        return repository.save(newView);
    }
    
    @GetMapping("/views/{id}")
    View one(@PathVariable Long id) {
        
        return repository.findById(id)
            .orElseThrow(() -> new ViewNotFoundException(id));
    }

    @PutMapping("/views/{id}")
    View replaceView(@RequestBody View newView, @PathVariable Long id) {
        
        return repository.findById(id)
        .map(view -> {
            view.setPseudo(newView.getPseudo());
            view.setComment(newView.getComment());
            view.setIsVisible(newView.getIsVisible());
            return repository.save(view);
        })
        .orElseGet(() -> {
            newView.setId(id);
            return repository.save(newView);
        });
    }

    @DeleteMapping("/views/{id}")
    void deleteView(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
}
