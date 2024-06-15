package com.api.APIMarcheAvecEliane;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
