package fr.ecf.arcadia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ecf.arcadia.pojo.Breed;

public interface BreedRepository extends JpaRepository<Breed, Long>  {

}
