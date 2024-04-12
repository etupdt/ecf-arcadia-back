package fr.ecf.arcadia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ecf.arcadia.pojo.Image;

public interface Repository extends JpaRepository<Image, Long>  {

}
