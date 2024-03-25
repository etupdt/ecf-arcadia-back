package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;

import fr.ecf.arcadia.pojo.Animal;
import fr.ecf.arcadia.pojo.Habitat;
import fr.ecf.arcadia.pojo.Image;
import fr.ecf.arcadia.pojo.Service;

@TestConfiguration
@ContextConfiguration("text-context.xml")
public class ControllersTestContextConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ControllersTestContextConfiguration.class);

    @BeforeAll
    static void setUp() {
        
        logger.info("===========> before all conf");
               
    }
    
    static Habitat savane = new Habitat(
        "Savane",
        "Sec, chaud et aride",
        "Sec et chaud",
        List.of(new  Image("image.jpg"))
    );
    
    @Bean
    static Habitat savane() {

        return savane;

    }

    @Bean
    static List<Habitat> habitats() {

        List<Habitat> habitats = List.of(savane);

        return habitats;

    }

    static Animal flash = new Animal(
        "Flash",
        "Bonne",
        List.of(new  Image("image.jpg"))
    );    

    @Bean
    static Animal flash() {

        return flash;

    }


    @Bean
    static List<Animal> animals() {

        List<Animal> animals = List.of(flash);

        return animals;

    }

    static Service restaurant = new Service(
        "Restaurant",
        "Un guide vous fera découvrir tous les habitats."
    );    

    @Bean
    static Service restaurant() {

        return restaurant;

    }


    @Bean
    static List<Service> services() {

        List<Service> services = List.of(restaurant);

        return services;

    }

    @Bean
    static MockMultipartFile image() {
        return new MockMultipartFile(
            "file", 
            "fichier .txt de test", 
            MediaType.TEXT_PLAIN_VALUE, 
            "Hello, World!".getBytes()
        );
    }

}

