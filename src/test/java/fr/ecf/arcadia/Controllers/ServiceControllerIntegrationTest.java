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

import fr.ecf.arcadia.Services.ServiceService;
import fr.ecf.arcadia.pojo.Service;

import org.mockito.Mockito;

import static org.hamcrest.Matchers.*;

@WebMvcTest({
    ServiceController.class
})
@TestPropertySource("classpath:application-integrationtest.properties")
@Import(ControllersTestContextConfiguration.class)
public class ServiceControllerIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(ServiceControllerIntegrationTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ServiceService serviceService;

    @Autowired
    private Service restaurant;

    @Autowired
    private MockMultipartFile image;

    @Autowired
    private List<Service> services;

    @Test
    public void givenControllers_whenGetServices_thenStatus200() throws Exception {

        this.testControllerPost();
        this.testControllerGets();
        
    }
    
    public void testControllerPost() throws Exception {

        logger.info("===========> executing post service test");

        Mockito.when(serviceService.addService(Mockito.any(Service.class))).thenReturn(restaurant);

        mvc.perform(MockMvcRequestBuilders.post("/api/services")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(restaurant))
                )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Restaurant")));
    }
    
    public void testControllerGets() throws Exception {

        logger.info("===========> executing gat services test");

        Mockito.when(serviceService.getAllServices()).thenReturn(services);

        mvc.perform(MockMvcRequestBuilders.get("/api/services")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(01)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Restaurant")));
  
    }
    
}