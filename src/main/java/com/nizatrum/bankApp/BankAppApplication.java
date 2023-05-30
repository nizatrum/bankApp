package com.nizatrum.bankApp;
import com.nizatrum.bankApp.models.Client;
import com.nizatrum.bankApp.repositories.ClientRepository;
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
	CommandLineRunner commandLineRunner(ClientRepository clients, PasswordEncoder passwordEncoder) {
		return args -> {
			if (clients.count() == 0) {
				clients.save(new Client("testUser@gmail.com",
						"user",
						"user",
						"user",
						"user",
						passwordEncoder.encode("password"),
						"ROLE_USER"));
				clients.save(new Client("testAdmin@gmail.com",
						"admin",
						"admin",
						"admin",
						"admin",
						passwordEncoder.encode("password"),
						"ROLE_ADMIN,ROLE_USER"));
			}
		};
	}

}
