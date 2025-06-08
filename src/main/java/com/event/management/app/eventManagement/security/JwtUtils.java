//package com.event.management.app.eventManagement.security;
//
//import io.jsonwebtoken.*;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtUtils {
//  private final String SECRET = "mySecretKey";
//  private final long EXPIRATION = 86400000; // 24h
//
//  public String generateToken(String username) {
//    return Jwts.builder()
//      .setSubject(username)
//      .setIssuedAt(new Date())
//      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//      .signWith(SignatureAlgorithm.HS256, SECRET)
//      .compact();
//  }
//
//  public String getUsernameFromToken(String token) {
//    return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
//  }
//
//  public boolean validateToken(String token) {
//    try {
//      Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
//      return true;
//    } catch (JwtException e) {
//      return false;
//    }
//  }
//}
