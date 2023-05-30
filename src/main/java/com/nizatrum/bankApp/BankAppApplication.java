package com.nizatrum.bankApp;
import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.repositories.ClientRepository;
import com.nizatrum.bankApp.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class BankAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ClientRepository clients, RoleRepository roles, PasswordEncoder encoder) {
		return args -> {
			if (clients.count() == 0) {

				clients.save(
						new Client("testUser@gmail.com",
								"user",
								"user",
								"user",
								"user",
								encoder.encode("password"),
								roles.findBySystemName("ROLE_USER")
						));
				clients.save(
						new Client("testAdmin@gmail.com",
								"admin",
								"admin",
								"admin",
								"admin",
								encoder.encode("password"),
								roles.findBySystemName("ROLE_ADMIN,ROLE_USER")
						));
			}
		};
	}
}
