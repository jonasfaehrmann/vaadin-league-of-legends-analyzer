

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "de.leuphana.leuphanytics.controller","de.leuphana.leuphanytics.view"})
public class LeuphanyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeuphanyticsApplication.class, args);
	}
}
