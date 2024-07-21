package fr.ecf.arcadia.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;

import fr.ecf.arcadia.pojo.AnimalStatistic;

public interface AnimalStatisticCustomRepository {

    void findAndIncrementStatisticsByFirstname(AnimalStatistic animalStatistic); 
    // List<AnimalStatistic> findAllSortByDate(Sort sort);

}
