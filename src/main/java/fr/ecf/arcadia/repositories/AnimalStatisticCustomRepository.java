package fr.ecf.arcadia.repositories;

import fr.ecf.arcadia.pojo.AnimalStatistic;

public interface AnimalStatisticCustomRepository {

    void findAndIncrementStatisticsByFirstname(AnimalStatistic animalStatistic); 
    // List<AnimalStatistic> findAllSortByDate(Sort sort);

}
