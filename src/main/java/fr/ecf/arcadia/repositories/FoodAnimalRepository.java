package fr.ecf.arcadia.repositories;

import java.util.List;

import org.hibernate.persister.collection.mutation.RowMutationOperations.Restrictions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.repository.query.Param;

import fr.ecf.arcadia.pojo.FoodAnimal;

public interface FoodAnimalRepository extends JpaRepository<FoodAnimal, Long>  {

    List<FoodAnimal> findAllByAnimalId(Long animalId);
    
}
