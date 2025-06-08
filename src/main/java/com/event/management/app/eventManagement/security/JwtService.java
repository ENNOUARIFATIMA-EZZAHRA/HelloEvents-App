package com.event.management.app.eventManagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

  private final String SECRET_KEY = "mySuperSecretKeyMySuperSecretKey123456"; // clé secrète d'au moins 256 bits
  private final long EXPIRATION = 1000 * 60 * 60; // 1 heure en ms

  private Key getSignInKey() {
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
  }

  public String extractUsername(String token) {
    return extractAllClaims(token).getSubject();
  }

  @SuppressWarnings("unchecked")
  public List<String> extractRoles(String token) {
    Claims claims = extractAllClaims(token);
    return claims.get("roles", List.class);
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    return extractAllClaims(token).getExpiration().before(new Date());
  }

  private Claims extractAllClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignInKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  // Méthode générant le token JWT avec les rôles extraits de UserDetails
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();

    // Récupération des rôles depuis UserDetails
    List<String> roles = userDetails.getAuthorities()
      .stream()
      .map(auth -> auth.getAuthority())
      .collect(Collectors.toList());

    claims.put("roles", roles);

    return createToken(claims, userDetails.getUsername());
  }

  // Création du token JWT signé
  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
      .setClaims(claims)
      .setSubject(subject)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
      .signWith(getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }
}
