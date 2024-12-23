package fr.ecf.arcadia.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import fr.ecf.arcadia.security.configuration.MongoAppConfig;

import fr.ecf.arcadia.pojo.AnimalStatistic;

public class AnimalStatisticCustomRepositoryImpl implements AnimalStatisticCustomRepository {

    @Autowired
    MongoAppConfig mongoAppConfig;

    public void findAndIncrementStatisticsByFirstname(AnimalStatistic animalStatistic) {
        Query query = new Query(Criteria.where("firstname").is(animalStatistic.getFirstname()).and("date").is(animalStatistic.getDate()));
        Update updateDefinition = new Update().inc("qty", 1).set("date", animalStatistic.getDate());
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
        mongoAppConfig.mongoTemplate().findAndModify(query, updateDefinition, options, AnimalStatistic.class);
    }

    // @Override
    // public List<AnimalStatistic> findAllSortByDate(Sort sort) {
    //     Query query = new Query().with(Sort.by(Sort.Direction.ASC, "date"));
    //     return mongoAppConfig.mongoTemplate().find(query, AnimalStatistic.class);
    // } 

}