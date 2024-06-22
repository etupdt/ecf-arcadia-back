package fr.ecf.arcadia.Controllers;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ecf.arcadia.Services.AnimalService;
import fr.ecf.arcadia.pojo.Animal;
import org.mockito.Mockito;
import static org.hamcrest.Matchers.*;

@WebMvcTest({
    AnimalController.class
})
@TestPropertySource("classpath:application-integrationtest.properties")
@Import(ControllersTestContextConfiguration.class)
public class AnimalControllerIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(AnimalControllerIntegrationTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AnimalService animalService;

    @Autowired
    private Animal flash;

    @Autowired
    private MockMultipartFile image;

    @Autowired
    private List<Animal> animals;

    @Test
    public void givenControllers_whenGetAnimals_thenStatus200() throws Exception {

        this.testControllerPost();
        this.testControllerGets();
        
    }
    
    public void testControllerPost() throws Exception {

        logger.info("===========> executing post animal test");

        // Mockito.when(animalService.addAnimal(Mockito.any(MultipartFile.class), Mockito.any(String.class))).thenReturn(flash);

        mvc.perform(MockMvcRequestBuilders.multipart("/api/animals")
                .file("file", image.getBytes())
                .param("animalInText", new ObjectMapper().writeValueAsString(flash))
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", is("Flash")));
  
    }
    
    public void testControllerGets() throws Exception {

        logger.info("===========> executing gat animals test");

        Mockito.when(animalService.getAllAnimals()).thenReturn(animals);

        mvc.perform(MockMvcRequestBuilders.get("/api/animals")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(01)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstname", is("Flash")));
  
    }
    
}