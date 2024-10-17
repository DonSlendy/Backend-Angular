package com.crud.api.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtUtilService {
    private static final Key JWT_SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long JWT_TIME_VALIDITY = 1000 * 60 * 2;
    private static final long JWT_TIME_REFRESH_VALIDITY = 1000 * 60 * 60 * 24;

    public String generateToken(UserDetails userDetails, String role){
        var claims = new HashMap<String,Object>();
        claims.put("rol",role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TIME_VALIDITY))
                .signWith(JWT_SECRET_KEY)
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails, String role){
        var claims = new HashMap<String,Object>();
        claims.put("rol",role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TIME_VALIDITY))
                .signWith(JWT_SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
        return extractClaim(token, Claims::getSubject).equals(userDetails.getUsername())
                && !extractClaim(token,Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).build().parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }
}
