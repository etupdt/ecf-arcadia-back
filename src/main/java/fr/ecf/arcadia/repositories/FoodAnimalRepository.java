package fr.ecf.arcadia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ecf.arcadia.pojo.FoodAnimal;

public interface FoodAnimalRepository extends JpaRepository<FoodAnimal, Long>  {

}
