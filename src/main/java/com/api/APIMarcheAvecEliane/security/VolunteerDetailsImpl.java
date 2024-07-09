package com.api.APIMarcheAvecEliane.security;

import com.api.APIMarcheAvecEliane.model.Volunteer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class VolunteerDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Volunteer volunteer;

    public VolunteerDetailsImpl(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_VOLUNTEER"));
        // with role
        // return List.of(new SimpleGrantedAuthority(role.name()));
    }
    // return a list of roles

    @Override
    public String getPassword() {
        return volunteer.getPassword();
    }

    @Override
    public String getUsername() {
        return volunteer.getEmailVolunteer();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

