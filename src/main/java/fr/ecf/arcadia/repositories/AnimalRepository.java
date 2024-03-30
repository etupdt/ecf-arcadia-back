package fr.ecf.arcadia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ecf.arcadia.pojo.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>  {

}
