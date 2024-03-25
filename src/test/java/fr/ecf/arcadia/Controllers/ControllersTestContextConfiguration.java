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

import fr.ecf.arcadia.pojo.Habitat;
import fr.ecf.arcadia.pojo.Image;

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

