package fr.ecf.arcadia.Services;

import java.util.List;

import fr.ecf.arcadia.pojo.View;

public interface ViewService {

        public List<View> getAllViews();
        public View addView(View newView);
        public View getView(Long id);
        public View updateView(View newView, Long id);
        public void deleteView(Long id);

}
