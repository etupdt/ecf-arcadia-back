package fr.ecf.arcadia;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.ecf.arcadia.Controllers.AnimalController;
import fr.ecf.arcadia.pojo.AnimalStatistic;
import fr.ecf.arcadia.repositories.AnimalStatisticRepository;

/** Integration tests for {@link AnimalController} */
@SpringBootTest
@AutoConfigureMockMvc
// @AutoConfigureDataMongo
// @DataMongoTest
@ActiveProfiles("test")
// @Transactional
public class AnimalControllerIT {
 
    private MockMvc mocMvc;

    @Autowired
    public AnimalControllerIT(MockMvc mocMvc) {
        this.mocMvc = mocMvc;
    }
 
    private AnimalStatisticRepository animalStatisticRepository;
    
    @Test
    public void postStatistic() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        mocMvc.perform(MockMvcRequestBuilders.post("/api/animals/statistics")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(new AnimalStatistic("Jumbo", "2024-11-02", 3L)))
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();

    }

    // @AfterEach
    // public void clearData() {
    //     // This function clear the user Data after each testcase
    //     animalRepository.deleteAll();
    // }
}