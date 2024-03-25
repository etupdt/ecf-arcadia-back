package fr.ecf.arcadia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ecf.arcadia.pojo.Habitat;

public interface HabitatRepository extends JpaRepository<Habitat, Long>  {

    // @Query("SELECT h FROM Habitat h WHERE name = ?1")
    // Habitat findByName(String name);

}
