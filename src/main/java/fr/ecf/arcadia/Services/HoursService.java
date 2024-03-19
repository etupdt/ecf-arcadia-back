package fr.ecf.arcadia.Services;

import java.util.List;

import fr.ecf.arcadia.pojo.Hours;

public interface HoursService {

        public List<Hours> getAllHourss();
        public Hours addHours(Hours newHours);
        public Hours getHours(Long id);
        public Hours updateHours(Hours newHours, Long id);
        public void deleteHours(Long id);

}
