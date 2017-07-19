package de.leuphana.leuphalytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import de.leuphana.leuphalytics.model.Dashboard;
import de.leuphana.leuphalytics.model.user.ExternalAccountEnum;
import de.leuphana.leuphalytics.model.user.User;
import de.leuphana.leuphalytics.model.widget.matchlist.RiotMatchList;

@SpringBootApplication
@ComponentScan(basePackages = { "de.leuphana.leuphalytics", "de.leuphana.leuphalytics.model",
		"de.leuphana.leuphalytics.view", "de.leuphana.leuphalytics.controller" })
public class LeuphalyticsApplication {

	private static final Logger log = LoggerFactory.getLogger(LeuphalyticsApplication.class);

	User user;
	Dashboard dashboard;

	public static void main(String[] args) {
		SpringApplication.run(LeuphalyticsApplication.class);
	}

}