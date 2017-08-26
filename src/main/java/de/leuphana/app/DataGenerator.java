package de.leuphana.app;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.backend.AccountRepository;
import de.leuphana.backend.AccountRoleRepository;
import de.leuphana.backend.data.entity.Account;
import de.leuphana.backend.data.entity.AccountRole;

/**
 * 
 * @author Jonas Fährmann
 *
 */

@SpringComponent
public class DataGenerator implements HasLogger {

	@Bean
	public CommandLineRunner loadData(AccountRepository accountRepository,
			AccountRoleRepository accountRoleRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (hasData(accountRepository)) {
				getLogger().info("Using existing database");
				return;
			}

			getLogger().info("Generating demo data");
			getLogger().info("... generating accountRoles");
			createAccountRole(accountRoleRepository);
			getLogger().info("... generating accounts");
			createUsers(accountRepository, passwordEncoder);
	
			getLogger().info("Generated demo data");
		};
	}

	private boolean hasData(AccountRepository accountRepository) {
		return accountRepository.count() != 0L;
	}

	private void createAccountRole(AccountRoleRepository accountRoleRepository){
		accountRoleRepository.save(new AccountRole("admin"));
		accountRoleRepository.save(new AccountRole("user"));
	}
	

	private void createUsers(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
		Account admin = new Account("admin@vaadin.com", "admin", passwordEncoder.encode("admin"), new AccountRole("admin"));
		Account user = new Account("user@vaadin.com", "Malin", passwordEncoder.encode("user"), new AccountRole("user"));
		accountRepository.save(admin);
		accountRepository.save(user);
	}
}
