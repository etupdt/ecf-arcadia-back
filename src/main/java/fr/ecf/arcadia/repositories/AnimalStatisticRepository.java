package fr.ecf.arcadia.repositories;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import fr.ecf.arcadia.pojo.AnimalStatistic;

public interface AnimalStatisticRepository extends MongoRepository<AnimalStatistic, String>, AnimalStatisticCustomRepository {

    @Update("{ '$inc' : { 'qty' : 1 } }")
    void findAndIncrementStatisticsByFirstname(AnimalStatistic animalStatistic); 

    // @Query
    // List<AnimalStatistic> findAllSortByDate(Sort sort);

}