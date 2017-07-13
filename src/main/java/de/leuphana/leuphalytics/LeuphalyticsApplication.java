package de.leuphana.leuphalytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import de.leuphana.leuphalytics.model.Dashboard;
import de.leuphana.leuphalytics.model.user.ExternalAccountEnum;
import de.leuphana.leuphalytics.model.user.User;
import de.leuphana.leuphalytics.model.widget.MatchList;
import de.leuphana.leuphalytics.model.widget.RiotMatchList;

@SpringBootApplication
public class LeuphalyticsApplication {
	
	private static final Logger log = LoggerFactory.getLogger(LeuphalyticsApplication.class);
	
	User user;
	Dashboard dashboard;

	public static void main(String[] args) {
		SpringApplication.run(LeuphalyticsApplication.class);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		
		// get account type...
		ExternalAccountEnum accountType = ExternalAccountEnum.RIOT;
		
		switch (accountType) {
		case RIOT:
			
			return args -> {
				
				MatchList matchList = new RiotMatchList();
				
			};
			
			break;
		}
		
	}
	
	
	
}
