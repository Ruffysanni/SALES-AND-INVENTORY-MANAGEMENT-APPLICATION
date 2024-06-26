package com.INGRYD.INGRYD_CRM.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Component
public class JWTService {
    //Step 1
    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    //Step 2
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    //Step 3
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    //Step 4
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    //Step 5
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    //Step 6
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    public String createToken(UserDetails userDetails) {
        return  generateToken(new HashMap<>(), userDetails);
    }
    //Step 7
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && isTokenExpired(token);
    }
    //Step 8
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    //Step 9
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
