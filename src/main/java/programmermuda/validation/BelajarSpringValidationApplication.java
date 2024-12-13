package programmermuda.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import programmermuda.validation.properties.DatabaseProperties;

@EnableConfigurationProperties({
		DatabaseProperties.class
})
@SpringBootApplication
public class BelajarSpringValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringValidationApplication.class, args);
	}

}
