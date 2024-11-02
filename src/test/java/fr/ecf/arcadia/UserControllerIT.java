package fr.ecf.arcadia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.ecf.arcadia.Controllers.UserController;
import jakarta.transaction.Transactional;

/** Integration tests for {@link UserController} */
@SpringBootTest
@AutoConfigureMockMvc
// @AutoConfigureDataMongo
// @ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
// @TestPropertySource(locations="classpath:application-test.properties")
// @Transactional
// @ContextConfiguration(classes = )
public class UserControllerIT {
 
    private MockMvc mocMvc;

    @Autowired
    public UserControllerIT(MockMvc mocMvc) {
        this.mocMvc = mocMvc;
    }
 
    
    // private UserRepository userRepository;
    
    // @MockBean
    // private final UserRepository userRepository;

    // @Autowired
    // UserService userService;

    // @MockBean
    // private ExternalDependencyService externalDependencyService;

    @Test
    public void getAdmin() throws Exception {

        mocMvc.perform(MockMvcRequestBuilders.get("/api/breeds"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()")
            .value(6));

    }

    // public void userServiceIntegrationTest(List<User> inputUserList, List<Integer> userIds, List<User> expectedUserList, List<Integer> externalDepencyFunctionInput, List<Integer> externalDependecyFunctionOutput) throws Exception {
    //     userRepository.saveAll(inputUserList);

    //     given(externalDependencyService.functionName(
    //         externalDepencyFunctionInput)
    //         .willReturn(externalDependecyFunctionOutput));
       
    //     userService.processUsers(userIds);

    //     List<User> actualUserList = userRepository.findAll();

    //     Assertions.assertThat(expectedUserList).containsExactlyInAnyOrderElementsOf(actualUserList);
    // }

    // @AfterEach
    // public void clearData() {
    //     // This function clear the user Data after each testcase
    //     userRepository.deleteAll();
    // }
}