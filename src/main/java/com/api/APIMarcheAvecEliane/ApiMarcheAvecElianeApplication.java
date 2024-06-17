package com.api.APIMarcheAvecEliane;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiMarcheAvecElianeApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiMarcheAvecElianeApplication.class, args);

		// Load "env.var" from ".env" file
		Dotenv dotenv = Dotenv.configure().load();

		// get "bdd password" from "env.var"
		String password = dotenv.get("SPRING_DATASOURCE_PASSWORD");
		System.setProperty("spring.datasource.password", password);
	}
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("🟢🟢 Hello World from Spring Boot! 🟢🟢");
		};
	}
}