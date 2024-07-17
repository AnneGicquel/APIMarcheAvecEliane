package com.api.APIMarcheAvecEliane.config;

import com.api.APIMarcheAvecEliane.model.Coordinator;
import com.api.APIMarcheAvecEliane.model.Volunteer;
import com.api.APIMarcheAvecEliane.repository.VolunteerRepository;
import com.api.APIMarcheAvecEliane.repository.CoordinatorRepository;
import com.api.APIMarcheAvecEliane.security.CoordinatorDetailsImpl;
import com.api.APIMarcheAvecEliane.security.VolunteerDetailsImpl;
import com.api.APIMarcheAvecEliane.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final VolunteerRepository volunteerRepository;
    private final CoordinatorRepository coordinatorRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Attempt to find coordinator first
            Coordinator coordinator = coordinatorRepository.findByEmail(username)
                    .orElse(null);

            if (coordinator != null) {
                return new CoordinatorDetailsImpl(coordinator);
            }

            // If coordinator not found, attempt to find volunteer
            Volunteer volunteer = volunteerRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

            return new VolunteerDetailsImpl(volunteer);
        };
    }
    //Coordinator et Volunteer Lookup : Nous essayons d'abord de trouver un coordonnateur en utilisant coordinatorRepository.findByEmail(username). Si un coordonnateur est trouvé, nous créons une instance de CoordinatorDetailsImpl avec les détails du coordonnateur et la retournons.
    //
    //Fallback to Volunteer : Si aucun coordonnateur n'est trouvé (coordinator == null), nous essayons alors de trouver un volontaire en utilisant volunteerRepository.findByEmail(username). Si un volontaire est trouvé, nous créons une instance de VolunteerDetailsImpl avec les détails du volontaire et la retournons.
    //
    //Exception Handling : Si ni un coordonnateur ni un volontaire ne sont trouvés, nous lançons une UsernameNotFoundException indiquant que l'utilisateur n'a pas été trouvé.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(); // fetch userdetails
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    // "manage"  authenticaction
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
