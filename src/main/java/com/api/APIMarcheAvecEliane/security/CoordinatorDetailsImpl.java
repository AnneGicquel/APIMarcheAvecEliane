package com.api.APIMarcheAvecEliane.security;

import com.api.APIMarcheAvecEliane.model.Coordinator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CoordinatorDetailsImpl implements UserDetails {
    private Coordinator coordinator;

    public CoordinatorDetailsImpl(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_COORDINATOR"));
    }

    @Override
    public String getPassword() {
        return coordinator.getPassword();
    }

    @Override
    public String getUsername() {
        return coordinator.getEmail();
    }

    // implémentations des autres méthodes de UserDetails
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
