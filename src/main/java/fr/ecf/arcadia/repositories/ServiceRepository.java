package fr.ecf.arcadia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ecf.arcadia.pojo.Service;

public interface ServiceRepository extends JpaRepository<Service, Long>  {

}
