package fr.ecf.arcadia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@ComponentScan("fr.ecf.arcadia")
// @EnableWebMvc
public class StaticFilesConfig implements WebMvcConfigurer{

    @Autowired
    private Environment environment;    

    private static final Logger logger = LoggerFactory.getLogger(StaticFilesConfig.class);
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
        .addResourceHandler("/images2/**")
        .addResourceLocations("file://" + environment.getProperty("server.imagesFilePath"));
    }

}
