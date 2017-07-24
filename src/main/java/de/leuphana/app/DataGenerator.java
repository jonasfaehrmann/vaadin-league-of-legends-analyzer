package de.leuphana.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.spring.annotation.SpringComponent;
import de.leuphana.backend.OrderRepository;
import de.leuphana.backend.PickupLocationRepository;
import de.leuphana.backend.ProductRepository;
import de.leuphana.backend.UserRepository;
import de.leuphana.backend.data.OrderState;
import de.leuphana.backend.data.Role;
import de.leuphana.backend.data.entity.Customer;
import de.leuphana.backend.data.entity.HistoryItem;
import de.leuphana.backend.data.entity.Order;
import de.leuphana.backend.data.entity.OrderItem;
import de.leuphana.backend.data.entity.PickupLocation;
import de.leuphana.backend.data.entity.Product;
import de.leuphana.backend.data.entity.User;

@SpringComponent
public class DataGenerator implements HasLogger {

	private User user;

	@Bean
	public CommandLineRunner loadData(OrderRepository orderRepository, UserRepository userRepository,
			ProductRepository productRepository, PickupLocationRepository pickupLocationRepository,
			PasswordEncoder passwordEncoder) {
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
