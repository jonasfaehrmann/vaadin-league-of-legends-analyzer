package de.leuphana.leuphalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.leuphana.leuphalytics.model.Dashboard;
import de.leuphana.leuphalytics.model.user.User;

@SpringBootApplication
public class LeuphalyticsApplication {
	
	User user;
	Dashboard dashboard;

	public static void main(String[] args) {
		SpringApplication.run(LeuphalyticsApplication.class, args);
	}
}
