package in.nvijaykarthik.fccserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import in.nvijaykarthik.fccserver.repository.FeatureActivationConfigRepo;
import in.nvijaykarthik.fccserver.service.FeatureProcessingService;

@SpringBootApplication
@EnableJpaRepositories
public class FccServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FccServerApplication.class, args);
	}

	@Bean
	public FeatureProcessingService  featureProcessingService(FeatureActivationConfigRepo activationConfigRepo){
		return new FeatureProcessingService(activationConfigRepo);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}
}
