package com.api.APIMarcheAvecEliane.auth;

import com.api.APIMarcheAvecEliane.auth.AuthenticationRequest;
import com.api.APIMarcheAvecEliane.auth.AuthenticationResponse;
import com.api.APIMarcheAvecEliane.auth.RegisterRequest;
import com.api.APIMarcheAvecEliane.model.Coordinator;
import com.api.APIMarcheAvecEliane.model.Volunteer;
import com.api.APIMarcheAvecEliane.repository.CoordinatorRepository;
import com.api.APIMarcheAvecEliane.repository.VolunteerRepository;
import com.api.APIMarcheAvecEliane.security.CoordinatorDetailsImpl;
import com.api.APIMarcheAvecEliane.security.VolunteerDetailsImpl;
import com.api.APIMarcheAvecEliane.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final VolunteerRepository volunteerRepository;
    private final CoordinatorRepository coordinatorRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        // VERSION CUSTOM
        if ("VOLUNTEER".equalsIgnoreCase(request.getRole())) {
            var volunteer = Volunteer.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .addressStreet(request.getAddressStreet())
                    .addressCity(request.getAddressCity())
                    .zipCode(request.getZipCode())
                    .mobileNumber(request.getMobileNumber())
                    .landlineNumber(request.getLandlineNumber())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .dateOfBirth(request.getDateOfBirth())
                    .entryDate(request.getEntryDate())
                    .build();
            volunteerRepository.save(volunteer);
            var jwtToken = jwtService.generateToken(new VolunteerDetailsImpl(volunteer));
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();

            //{
            //    "firstname": "TEST",
            //    "lastname": "BEST",
            //    "addressStreet": "BRAVO !!!",
            //    "addressCity": "Paris",
            //    "zipCode": "75019",
            //    "mobileNumber": "01 60 75 90 62",
            //    "landlineNumber": "06 20 81 81 56",
            //    "email": "aeonbellet@gmail.com",
            //    "password": "JeSuisUneStar<3",
            //    "dateOfBirth": "2005-11-13T23:00:00.000+00:00",
            //    "entryDate": "2023-07-19T22:00:00.000+00:00",
            //    "role": "VOLUNTEER"
            //}


        } else if ("COORDINATOR".equalsIgnoreCase(request.getRole())) {
            var coordinator = Coordinator.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            coordinatorRepository.save(coordinator);
            var jwtToken = jwtService.generateToken(new CoordinatorDetailsImpl(coordinator));
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new IllegalArgumentException("Invalid role specified");
        }

        //{
        //            "firstname": "Sophie",
        //                    "lastname": "Gicquel",
        //                    "email": "sofie.gicquel@mae.fr",
        //                    "password": "@CryptéSVP333!!",
        //                    "role": "COORDINATOR"
        //        }
    }


    // VERSION USER
    // var user = User.builder ()
    //                .firstname(request.getFirstname())
    //                .lastname(request.getLastname())
    //                .email(request.getEmail())
    //                .password(passwordEncoder.encode(request.getPassword()))
    //                .role(Role.USER)
    //                .build();
    //        repository.save(user) ;
    //        var jwtToken = jwtService.generateToken(user);
    //        return AuthenticationResponse.builder()
    //              .token(jwtToken)
    //              .build();
    //    }


    //return null;
    //}


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        //🟦
        SecurityContextHolder.getContext().setAuthentication(authentication); // Définir l'authentification dans le contexte Spring Security

        // If i'm in this part, mean email & pwd are correct
        var userEmail = request.getEmail();
        var volunteerOpt = volunteerRepository.findByEmail(userEmail);
        var coordinatorOpt = coordinatorRepository.findByEmail(userEmail);




        // FONCTIONNE TRES BIEN !!!!!
        //var jwtToken = volunteerOpt.map(volunteer -> {
        //                    if (!passwordEncoder.matches(request.getPassword(), volunteer.getPassword())) {
        //                        throw new IllegalArgumentException("Invalid password");
        //                    }
        //                    var userDetails = new VolunteerDetailsImpl(volunteer);
        //                    return jwtService.generateToken(userDetails);
        //                }).orElseGet(() -> coordinatorOpt.map(coordinator -> {
        //                    if (!passwordEncoder.matches(request.getPassword(), coordinator.getPassword())) {
        //                        throw new IllegalArgumentException("Invalid password");
        //                    }
        //                    var userDetails = new CoordinatorDetailsImpl(coordinator);
        //                    return jwtService.generateToken(userDetails);
        //                }).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail)));
        //
        //                return AuthenticationResponse.builder()
        //                        .token(jwtToken)
        //                        .build();
        //            }
        //
        //        }

        //🟦
        String role = null;
        String jwtToken = null;

        if (volunteerOpt.isPresent()) {
            Volunteer volunteer = volunteerOpt.get();
            if (!passwordEncoder.matches(request.getPassword(), volunteer.getPassword())) {
                throw new IllegalArgumentException("Mot de passe invalide");
            }
            role = "VOLUNTEER";
            var userDetails = new VolunteerDetailsImpl(volunteer);
            jwtToken = jwtService.generateToken(userDetails);
        } else if (coordinatorOpt.isPresent()) {
            Coordinator coordinator = coordinatorOpt.get();
            if (!passwordEncoder.matches(request.getPassword(), coordinator.getPassword())) {
                throw new IllegalArgumentException("Mot de passe invalide");
            }
            role = "COORDINATOR";
            var userDetails = new CoordinatorDetailsImpl(coordinator);
            jwtToken = jwtService.generateToken(userDetails);
        } else {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + userEmail);
        }

        return new AuthenticationResponse(jwtToken, role);
    }
}



// VERSION USER
// public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        ); // exception to thrown
//
//        // If i'm in this part, mean email & pwd are correct
//        var user = volunteerRepository.findByEmail(request.getEmail())
//                .orElseThrow(); // handle exception
//        // once i am the volunteer or coordinator ; i ll generate token
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//
//    }
//}






