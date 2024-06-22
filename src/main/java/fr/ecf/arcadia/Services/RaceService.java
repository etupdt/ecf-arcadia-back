package fr.ecf.arcadia.Services;

import java.util.List;

import fr.ecf.arcadia.pojo.Race;

public interface RaceService {

        public List<Race> getAllRaces();
        public Race addRace(Race newRace);
        public Race getRace(Long id);
        public Race updateRace(Race newRace, Long id);
        public void deleteRace(Long id);

}
