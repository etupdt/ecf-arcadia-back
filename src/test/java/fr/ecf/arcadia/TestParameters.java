package fr.ecf.arcadia;

import java.util.List;

import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import lombok.Getter;
import lombok.Setter;

@TestConfiguration
@Getter
@Setter
public class TestParameters {

    private List<OngoingStubbing> whens; 
    private List<ResultActions> actions; 

    public TestParameters(List<OngoingStubbing> whens, List<ResultActions> actions) throws Exception {
        this.whens = whens;
        this.actions = actions;
    }

}
