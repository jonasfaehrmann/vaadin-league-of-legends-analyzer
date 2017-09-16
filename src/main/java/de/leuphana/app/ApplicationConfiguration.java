package de.leuphana.app;

import com.vaadin.spring.access.SecuredViewAccessControl;

import net.rithms.riot.api.ApiConfig;

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
	ApiConfig config(){
		return new ApiConfig().setKey("RGAPI-6bba33b3-e6cd-41c2-87fb-0cf7037f5105");
	}
}
