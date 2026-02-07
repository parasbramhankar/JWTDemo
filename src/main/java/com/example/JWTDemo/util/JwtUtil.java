package com.example.JWTDemo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {


    private static final String SECRET_KEY_STRING="parasbramhankar12345gjhgjhghggjhhgh";
    private final SecretKey SECRETE_KEY= Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    public String generateToken(String username){
        String token= Jwts.builder()
                        .subject(username)
                                .issuedAt(new Date())
                                        .expiration(new Date(System.currentTimeMillis()*1000*30))
                                                .signWith(SECRETE_KEY)
                                                        .compact();

        System.out.println("Generated token "+token);

        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(SECRETE_KEY)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e){
            System.out.println("Token Expired "+e.getMessage());
            return false;
        }catch (SignatureException e){
            System.out.println("Invalid Exception "+e.getMessage());
            return false;
        }
        catch (MalformedJwtException e){
            System.out.println("Invalid JWT token "+e.getMessage());
            return false;
        }
        catch (UnsupportedJwtException e){
            System.out.println("JWT token is unsupported "+e.getMessage());
            return false;
        }
        catch (JwtException e){
            System.out.println("JWT Exception "+e.getMessage());
            return false;
        }
    }

}
