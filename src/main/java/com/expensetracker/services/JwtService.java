package com.expensetracker.services;

import com.expensetracker.data.models.User;
import com.expensetracker.exceptions.TokenAlreadyExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //    To generate token for a user
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now()
                        .plus(Duration.ofHours(24)))) // Token valid for 24 hours
                .claim("userId", user.getUserId())
                .claim("firstname", user.getFirstname())
                .claim("lastname", user.getLastname())
                .claim("email", user.getEmail())
                .signWith(getSigningKey())
                .compact();
    }


    //    To check if a token is valid
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //    To check if a token is expired
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //    For extracting expiry date
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //    To extract user details such as the username upon request

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    //    To extract claims from the client request
    public Claims extractAllClaims(String jwtToken) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload();
        } catch (ExpiredJwtException ex) {
            throw new TokenAlreadyExpiredException("Token expired at "+ ex.getClaims().getExpiration());
        }
    }

    private SecretKey getSigningKey() {
        String secretKey = "06904e8dc8b08f33602356eb35f78f8fc32284d09688a199ce463be4692c9626";
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
