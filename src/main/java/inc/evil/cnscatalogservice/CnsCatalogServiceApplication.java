package inc.evil.cnscatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CnsCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CnsCatalogServiceApplication.class, args);
	}

}
