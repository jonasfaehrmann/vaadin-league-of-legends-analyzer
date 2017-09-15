package de.leuphana.app;

import com.vaadin.spring.access.SecuredViewAccessControl;

import java.util.ResourceBundle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {

	/**
	 * The password encoder to use when encrypting passwords.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecuredViewAccessControl securedViewAccessControl() {
		return new SecuredViewAccessControl();
	}
	
	@Bean
	ResourceBundle resourceBundle(){
		return ResourceBundle.getBundle("ApplicationRessources");
	}
}
