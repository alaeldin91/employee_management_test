package com.alaeldin.employee_anagement_service.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {


   private SecretKey secretKey;

   public JwtService( @Value("${app.jwt.secret}") String secretKey) {
       this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
   }

    /**
     * this Function generate Token Use jwt
     * @param userDetails UserDetails
     * @return String
     */
   public  String generateToken(UserDetails userDetails) {

       return Jwts.builder()
               .subject(userDetails.getUsername())
               .signWith(secretKey)
               .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
               .issuedAt(new Date(System.currentTimeMillis()))
               .compact();
   }

    public String extractUserName(String token){

       return Jwts.parser()
               .verifyWith(secretKey)
               .build()
               .parseSignedClaims(token)
               .getPayload().getSubject();
    }

    public String extractRole(String token){

     Claims claims = Jwts.parser()
               .verifyWith(secretKey)
               .build()
               .parseSignedClaims(token)
               .getPayload();
     Object role =  claims.get("role");

     return role !=null ? role.toString() : null;
    }
    public boolean validateToken(String token , UserDetails userDetails){

       Date exp = Jwts
               .parser()
               .verifyWith(secretKey)
               .build()
               .parseSignedClaims(token)
               .getPayload().getExpiration();

       String username = extractUserName(token);

       return username.equals(userDetails.getUsername()) && exp.after(new Date());
    }
}
