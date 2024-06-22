package fr.ecf.arcadia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ecf.arcadia.pojo.Habitat;

public interface HabitatRepository extends JpaRepository<Habitat, Long>  {

    // @Query("SELECT h FROM Habitat h WHERE name = ?1")
    // Habitat findByName(String name);

}
