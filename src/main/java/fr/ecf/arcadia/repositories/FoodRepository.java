package fr.ecf.arcadia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ecf.arcadia.pojo.Food;

public interface FoodRepository extends JpaRepository<Food, Long>  {

}
