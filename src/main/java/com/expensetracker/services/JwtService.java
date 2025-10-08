package com.expensetracker.services;

import com.expensetracker.data.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY =
            "06904e8dc8b08f33602356eb35f78f8fc32284d09688a199ce463be4692c9626";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //    To generate token for a user
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", user.getUserId());
        claims.put("firstname", user.getFirstname());
        claims.put("lastname", user.getLastname());
        claims.put("email", user.getEmail());
        claims.put("username", user.getUsername());
        Instant now = Instant.now();

        return Jwts.builder()
                .claims(claims)
                .setHeaderParam("typ", "JWT")
                .subject(user.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(Duration.ofHours(24))))
                .signWith(generateSignInKey(), SignatureAlgorithm.HS256)
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
        return Jwts
                .parser()
                .verifyWith(generateSignInKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public SecretKey generateSignInKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
