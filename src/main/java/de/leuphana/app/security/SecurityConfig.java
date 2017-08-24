package de.leuphana.app.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import de.leuphana.app.Application;
import de.leuphana.backend.data.entity.AccountRole;
import de.leuphana.backend.service.AccountRoleService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	private final PasswordEncoder passwordEncoder;

	private final RedirectAuthenticationSuccessHandler successHandler;
	
	private final AccountRoleService accountRoleService;

	@Autowired
	public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder,
			RedirectAuthenticationSuccessHandler successHandler, AccountRoleService accountRoleService) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.successHandler = successHandler;
		this.accountRoleService = accountRoleService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Not using Spring CSRF here to be able to use plain HTML for the login
		// page
		http.csrf().disable();

		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry reg = http
				.authorizeRequests();

		// Allow access to static resources ("/VAADIN/**")
		reg = reg.antMatchers("/VAADIN/**").permitAll();
		//Allow access to static resources ("/LEUPHALYTICS/**")
		reg = reg.antMatchers("/LEUPHALYTICS/**").permitAll();
		// Require authentication for all URLS ("/**")
		reg = reg.antMatchers("/**").hasAnyAuthority(accountRoleService.findAllAsStringArray());
		HttpSecurity sec = reg.and();

		// Allow access to login page without login
		FormLoginConfigurer<HttpSecurity> login = sec.formLogin().permitAll();
		login = login.loginPage(Application.LOGIN_URL).loginProcessingUrl(Application.LOGIN_PROCESSING_URL)
				.failureUrl(Application.LOGIN_FAILURE_URL).successHandler(successHandler);
		login.and().logout().logoutSuccessUrl(Application.LOGOUT_URL);
	}

}
