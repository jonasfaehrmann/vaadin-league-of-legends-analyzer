package de.leuphana.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.spring.annotation.SpringComponent;

import de.leuphana.backend.AccountRepository;
import de.leuphana.backend.AccountRoleRepository;
import de.leuphana.backend.data.account.Account;
import de.leuphana.backend.data.account.AccountRole;

/**
 * 
 * @author Jonas Fährmann
 *
 */

@SpringComponent
public class DataGenerator implements HasLogger {
	
	private final List<AccountRole> accountRoles = new ArrayList<>();

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
			createAccountRoles(accountRoleRepository);
			getLogger().info("... generating accounts");
			createUsers(accountRepository, passwordEncoder);
	
			getLogger().info("Generated demo data");
		};
	}

	private boolean hasData(AccountRepository accountRepository) {
		return accountRepository.count() != 0L;
	}

	private void createAccountRoles(AccountRoleRepository accountRoleRepository){
		AccountRole admin = new AccountRole();
		admin.setName("admin");
		accountRoles.add(accountRoleRepository.save(admin));
		AccountRole user = new AccountRole();
		user.setName("user");
		accountRoles.add(accountRoleRepository.save(user));
	}
	

	private void createUsers(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
		Account admin = new Account("admin@vaadin.com","admin", passwordEncoder.encode("admin"), accountRoles.get(0));
		accountRepository.save(admin);
		Account user = new Account("user@vaadin.com", "user", passwordEncoder.encode("user"), accountRoles.get(1));
		accountRepository.save(user);
	}
}
