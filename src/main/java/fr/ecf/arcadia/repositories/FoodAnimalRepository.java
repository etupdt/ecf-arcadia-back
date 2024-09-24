package fr.ecf.arcadia.repositories;

import java.util.List;

import org.hibernate.annotations.processing.Find;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ecf.arcadia.pojo.FoodAnimal;

public interface FoodAnimalRepository extends JpaRepository<FoodAnimal, Long>  {

    @Query("SELECT h FROM Habitat h WHERE id_animal = ?1")
    List<FoodAnimal> findAllFoodsAnimal(Long id_animal);

}
