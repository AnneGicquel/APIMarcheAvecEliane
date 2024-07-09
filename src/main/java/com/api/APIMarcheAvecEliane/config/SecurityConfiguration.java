package com.api.APIMarcheAvecEliane.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                //https://www.baeldung.com/spring-security-csrf


                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/api/register").permitAll() // Allow access to register endpoint
                                .requestMatchers("/api/authenticate").permitAll()
                                .requestMatchers("/api/volunteers/getAllVolunteers").hasRole("COORDINATOR")

                //TESTT
                //.requestMatchers("/api/elderlies/getAllElderlies").hasAnyRole("VOLUNTEER")
                //. FONCTIONNE PAS requestMatchers("/api/elderlies/getAllElderlies").authenticated()
                //. SURTOUT PASSSS anyRequest().permitAll()



                //.requestMatchers("/volunteer/excluded").denyAll() // page excluded for volunteer
                //.requestMatchers("/coordinator/specificPage").hasRole("COORDINATOR")

                //.🟡requestMatchers("/coordinator/**").hasRole("COORDINATOR")

                // specific page for coordinator

                //🟡.requestMatchers("/volunteer/**").hasRole("VOLUNTEER")
                //.requestMatchers("/coordinator/**").hasRole("COORDINATOR")
                //.requestMatchers("/login/**").permitAll()

                //🟡
                .anyRequest().authenticated())


                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}

// } TROP DEPRECATED !!! https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter