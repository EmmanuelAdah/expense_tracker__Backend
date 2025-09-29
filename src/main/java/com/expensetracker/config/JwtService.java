package com.expensetracker.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final Key secretKey = Keys
            .hmacShaKeyFor("06904e8dc8b08f33602356eb35f78f8fc32284d09688a199ce463be4692c9626"
                    .getBytes());

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //   To generate token without having to extract claims but from user details
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    //    To generate token for a user
    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    //    To check if a token is valid
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
    }

    //    To check if a token is expired
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //    For extracting expiry date
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    //    To extract user details such as the username upon request
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    //    To extract claims from the client request
    public Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
