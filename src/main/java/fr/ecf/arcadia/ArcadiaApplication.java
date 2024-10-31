package fr.ecf.arcadia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
// @ImportResource({"classpath*:test-context.xml"})
public class ArcadiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArcadiaApplication.class, args);
	}

}
