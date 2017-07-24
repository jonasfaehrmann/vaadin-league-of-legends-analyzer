package de.leuphana.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.spring.annotation.SpringComponent;
import de.leuphana.backend.UserRepository;
import de.leuphana.backend.data.Role;
import de.leuphana.backend.data.entity.User;

@SpringComponent
public class DataGenerator implements HasLogger {

	private User user;

	@Bean
	public CommandLineRunner loadData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (hasData(userRepository)) {
				getLogger().info("Using existing database");
				return;
			}

			getLogger().info("Generating demo data");
			getLogger().info("... generating users");
			createUsers(userRepository, passwordEncoder);
			getLogger().info("Generated demo data");
		};
	}

	private boolean hasData(UserRepository userRepository) {
		return userRepository.count() != 0L;
	}

	// private User getUser() {
	// return user;
	// }

	private void createUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		user = userRepository
				.save(new User("dams.bennett@gmail.com", "Bennett", passwordEncoder.encode("bennett"), Role.ADMIN));
		user.setLocked(true);
	}
}
