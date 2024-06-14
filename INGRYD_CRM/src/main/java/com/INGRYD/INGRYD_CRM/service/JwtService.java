package com.INGRYD.INGRYD_CRM.service;

import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY
            = "6QP+6UyKPPn69L51WLy7sNGVLzUywFFWDuAVhlxwP22fKyZf8MSOvDzMvIer5S+a9l06gdN5kTZ+KmdDeVvYxA==";

    private Key getSigningKey(){
        byte keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String createFreshToken(Map<String, Object> mapOfClaims, UserDetails userDetails) {
        return Jwts.builder()
                .addClaims(mapOfClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .setIssuer("Banking Application 1.0")
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String createToken(UserDetails userDetails){
        return createFreshToken(new HashMap<>(), userDetails);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractDateIssued(String token){
        return extractClaims(token, Claims::getIssuedAt);
    }

    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    private boolean isTokenGeneratedFromServer(String token){
        return extractClaims(token, Claims::getIssuer).equals("Banking App 1.0");
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractClaims(token, Claims::getSubject);
        return username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
