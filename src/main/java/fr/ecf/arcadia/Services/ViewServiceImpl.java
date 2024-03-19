package fr.ecf.arcadia.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ecf.arcadia.pojo.View;
import fr.ecf.arcadia.repositories.ViewRepository;

@Service
public class ViewServiceImpl implements ViewService {

    @Autowired
    private ViewRepository repository;

    public ViewServiceImpl () {
    }

    @Override
    public List<View> getAllViews() {
        return repository.findAll();
    }

    @Override
    public View addView(View view) {
        return repository.save(view);
    }
    
    @Override
    public View getView(@PathVariable Long id) {

        return repository.findById(id)
            .orElseThrow(() -> new ViewNotFoundException(id));
    }

    @Override
    public View updateView(View newView, Long id) {
        
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

    @Override
    public void deleteView(Long id) {
        repository.deleteById(id);
    }
    
}
