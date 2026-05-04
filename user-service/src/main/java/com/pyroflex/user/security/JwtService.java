package com.pyroflex.user.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET =
            "pyroflex_gym_work_secretkey_public_temporary_1234567890123456";

    // ✅ Use explicit charset + static key (avoid reload inconsistencies)
    private static final Key key =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String email) {

        Date now = new Date();
        Date expiry = new Date(System.currentTimeMillis() + 1000 * 60 * 60);

        return  Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractClaims(token);

            return claims.getExpiration().after(new Date());

        } catch (ExpiredJwtException e) {
            System.out.println(" EXPIRED TOKEN");
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            System.out.println(" UNSUPPORTED TOKEN");
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            System.out.println(" MALFORMED TOKEN");
            e.printStackTrace();
        } catch (SignatureException e) {
            System.out.println(" SIGNATURE INVALID (TAMPERED / KEY MISMATCH)");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(" EMPTY TOKEN");
            e.printStackTrace();
        }

        return false;
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)   // 🔐 signature verified here
                .getBody();
    }
}