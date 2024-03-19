package fr.ecf.arcadia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ecf.arcadia.pojo.Hours;

public interface HoursRepository extends JpaRepository<Hours, Long>  {

}
