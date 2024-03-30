package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ecf.arcadia.Services.ViewService;
import fr.ecf.arcadia.pojo.View;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.VIEW)
public class ViewController {

    @Autowired
    private ViewService viewService;

    @GetMapping
    public List<View> getAllViews() {
        return viewService.getAllViews();
    }

    @PostMapping
    public View newView(@RequestBody View view) {
        return viewService.addView(view);
    }
    
    @GetMapping("/{id}")
    public View one(@PathVariable Long id) {      
        return viewService.getView(id);
    }

    @PutMapping("/{id}")
    public View updateView(@RequestBody View view, @PathVariable Long id) {        
        return viewService.updateView(view, id);
    }

    @DeleteMapping("/{id}")
    public void deleteView(@PathVariable Long id) {
        viewService.deleteView(id);
    }
    
}
