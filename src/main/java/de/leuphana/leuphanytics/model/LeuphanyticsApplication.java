package de.leuphana.leuphanytics.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = { "de.leuphana.leuphanytics.controller","de.leuphana.leuphanytics.view", "de.leuphana.leuphanytics.model"})
public class LeuphanyticsApplication {
	
	private static final Logger log = LoggerFactory.getLogger(LeuphanyticsApplication.class);

	public static void main(String args[]) {
		SpringApplication.run(LeuphanyticsApplication.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Game game = restTemplate.getForObject(
					"http://gturnquist-quoters.cfapps.io/api/random", Game.class);
			log.info(game.toString());
		};
	}
}
