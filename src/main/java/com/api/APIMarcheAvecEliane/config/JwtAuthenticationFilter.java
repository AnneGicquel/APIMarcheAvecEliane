package com.api.APIMarcheAvecEliane.config;

import com.api.APIMarcheAvecEliane.service.CustomUserDetailsService;
import com.api.APIMarcheAvecEliane.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // create a constructor using final fields
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // OncePerRequestFilter => filter by every request
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    // overidded UserDetailsService to fetch our data
    // need a class to implement this Service
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,// our request
            HttpServletResponse response, // our response to the request
            FilterChain filterChain // chain of responsability pattern

    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");// extract header
        // Authorization also named 'Bearer token'

        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response); // pass the request to the next filter
            return;
        }
        // extract this jwt token from Authorization header
        jwt = authHeader.substring(7); // starting from position number 7 (Bearer count 7 char)
        userEmail = jwtService.extractUsername(jwt); // extract the userEmail from JWT token
        // user not authentificated yet
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) { // user not authenticated yet
            UserDetails userDetails = null;
            try {
                userDetails = this.customUserDetailsService.loadUserByUsername(userEmail);
            } catch (UsernameNotFoundException e) {
                // handle exception, user not found
                filterChain.doFilter(request, response);
                return;
            }
            if (jwtService.isTokenValid(jwt,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }

        filterChain.doFilter(request, response); // pass to the next filter



    }}
