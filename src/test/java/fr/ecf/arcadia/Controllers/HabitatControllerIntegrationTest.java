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

import fr.ecf.arcadia.Services.HabitatService;
import fr.ecf.arcadia.pojo.Habitat;

import org.mockito.Mockito;

import static org.hamcrest.Matchers.*;

@WebMvcTest({
    HabitatController.class
})
@TestPropertySource("classpath:application-integrationtest.properties")
@Import(ControllersTestContextConfiguration.class)
public class HabitatControllerIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(HabitatControllerIntegrationTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HabitatService habitatService;

    @Autowired
    private Habitat savane;

    @Autowired
    private MockMultipartFile image;

    @Autowired
    private List<Habitat> habitats;

    @Test
    public void givenControllers_whenGetHabitats_thenStatus200() throws Exception {

        this.testControllerPost();
        this.testControllerGets();
        
    }
    
    public void testControllerPost() throws Exception {

        logger.info("===========> executing post habitat test");

//        Mockito.when(habitatService.addHabitat(Mockito.any(MultipartFile.class), Mockito.any(String.class))).thenReturn(savane);

        mvc.perform(MockMvcRequestBuilders.multipart("/api/habitats")
                .file("file", image.getBytes())
                .param("habitatInText", new ObjectMapper().writeValueAsString(savane))
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Savane")));
    }
    
    public void testControllerGets() throws Exception {

        logger.info("===========> executing gat habitats test");

        Mockito.when(habitatService.getAllHabitats()).thenReturn(habitats);

        mvc.perform(MockMvcRequestBuilders.get("/api/habitats")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(01)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Savane")));
  
    }
    
}