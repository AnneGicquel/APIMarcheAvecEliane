package com.api.APIMarcheAvecEliane.service;

import com.api.APIMarcheAvecEliane.model.Coordinator;
import com.api.APIMarcheAvecEliane.model.Volunteer;
import com.api.APIMarcheAvecEliane.repository.CoordinatorRepository;
import com.api.APIMarcheAvecEliane.repository.VolunteerRepository;
import com.api.APIMarcheAvecEliane.security.CoordinatorDetailsImpl;
import com.api.APIMarcheAvecEliane.security.VolunteerDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final VolunteerRepository volunteerRepository;
    private final CoordinatorRepository coordinatorRepository;

    @Autowired
    public CustomUserDetailsService(VolunteerRepository volunteerRepository, CoordinatorRepository coordinatorRepository) {
        this.volunteerRepository = volunteerRepository;
        this.coordinatorRepository = coordinatorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Volunteer volunteer = volunteerRepository.findByEmail(email)
                .orElse(null);

        if (volunteer != null) {
            return new VolunteerDetailsImpl(volunteer);
        }

        Coordinator coordinator = coordinatorRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Coordinator not found with email: " + email));

        return new CoordinatorDetailsImpl(coordinator);
    }
}
