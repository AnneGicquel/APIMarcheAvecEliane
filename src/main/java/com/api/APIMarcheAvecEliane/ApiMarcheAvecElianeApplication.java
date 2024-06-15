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

		// Charger les variables d'environnement depuis le fichier .env
		Dotenv dotenv = Dotenv.configure().load();

		// Récupérer le mot de passe de la base de données depuis les variables d'environnement
		String password = dotenv.get("SPRING_DATASOURCE_PASSWORD");
		System.setProperty("spring.datasource.password", password);
	}
	@Bean
	// Bean pour afficher "Hello World" dans la console
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("🛑🛑Hello World from Spring Boot!🛑🛑");
		};
	}
}