package de.leuphana.app;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.vaadin.spring.events.annotation.EnableEventBus;

import de.leuphana.app.security.SecurityConfig;
import de.leuphana.backend.OrderRepository;
import de.leuphana.backend.data.entity.Order;
import de.leuphana.backend.data.entity.neww.Account;
import de.leuphana.backend.service.AccountService;
import de.leuphana.backend.util.LocalDateJpaConverter;
import de.leuphana.ui.AppUI;

@SpringBootApplication(scanBasePackageClasses = { AppUI.class, Application.class, AccountService.class,
		SecurityConfig.class })
//@EntityScan(basePackageClasses = { Order.class, LocalDateJpaConverter.class })
@EnableJpaRepositories("de.leupana.backend")
@EnableEventBus
public class Application extends SpringBootServletInitializer {

	public static final String APP_URL = "/";
	public static final String LOGIN_URL = "/login.html";
	public static final String LOGOUT_URL = "/login.html?logout";
	public static final String LOGIN_FAILURE_URL = "/login.html?error";
	public static final String LOGIN_PROCESSING_URL = "/login";

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}
