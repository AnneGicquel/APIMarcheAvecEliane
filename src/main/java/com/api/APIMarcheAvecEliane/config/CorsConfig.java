package com.api.APIMarcheAvecEliane.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }

}






//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:4200") // Ajoutez d'autres origines au besoin
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // Ajoutez d'autres méthodes au besoin
//                        .allowedHeaders("*") // Autorise tous les en-têtes
//                        .exposedHeaders("Header1", "Header2") // Expose certains en-têtes
//                        .allowCredentials(true) // Active les credentials
//                        .maxAge(3600); // Cache la réponse préalable pendant 1 heure
//            }
//        };
//    }