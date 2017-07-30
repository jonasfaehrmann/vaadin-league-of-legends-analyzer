package de.leuphana.leuphalytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.leuphana.leuphalytics.model.Dashboard;
import de.leuphana.leuphalytics.model.user.User;

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
