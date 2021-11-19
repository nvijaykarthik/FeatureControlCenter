package in.nvijaykarthik.fccserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FccServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FccServerApplication.class, args);
	}

}
