package com.api.APIMarcheAvecEliane.service;

import com.api.APIMarcheAvecEliane.model.Coordinator;
import com.api.APIMarcheAvecEliane.model.Volunteer;
import com.api.APIMarcheAvecEliane.security.CoordinatorDetailsImpl;
import com.api.APIMarcheAvecEliane.security.VolunteerDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    //private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    // value generate by encryption => 256 bytes => 64 char
    // in app.properties now

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    public String getSecretKey() {
        return secretKey;
    }
    // public void printSecretKey() {
    //System.out.println("🟡JWT Secret Key: " + secretKey);
    //}

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // generic method T
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token); // extract all the claims
        return claimsResolver.apply(claims); // all claims by function
    }

    // generate a token
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // username is our email
                .setIssuedAt(new Date(System.currentTimeMillis()))// help calculate if token valid or not
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact(); // generate and return the token

    }

    // generate a token only with userDetail
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    //extract the claims
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token has expired", e);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("Token is unsupported", e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Token is malformed", e);
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid token signature", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Token is null or empty", e);
        }
    }

    //🟣
    //🟣



    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);// algo of jwt
    };
}
