package fr.ecf.arcadia.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;

import fr.ecf.arcadia.pojo.AnimalStatistic;

public interface AnimalStatisticRepository extends MongoRepository<AnimalStatistic, String> {

    @Update("{ '$inc' : { 'qty' : 1 } }")
    long findAndIncrementStatisticsByFirstname(String firstname); 

}