package fr.ecf.arcadia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ecf.arcadia.pojo.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>  {

}
