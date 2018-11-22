package systems.health263.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"systems.health263.dashboard"})
@EntityScan({"systems.health263.dashboard"})
@EnableJpaRepositories({"systems.health263.dashboard"})
@SpringBootApplication
public class DashboardWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardWebApplication.class, args);
	}
}
