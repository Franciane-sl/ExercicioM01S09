package br.com.futuroDev.APISustentavel.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class JwtUtil {

    private final SecretKey SECRET_KAY = Keys.hmacShaKeyFor("8d9ae1e57e1932288c2a69715d55613cd7366d616ff107b7d88524152300fdad52f6846cd41812c952dddd4a0eb1de07f16368f9368defc4a53b05be5e79c30bff2d7acc8256cabcb1d9b70a3db8ed3f9ff5d0f543c1bbb28b88708c70d97003d84f11e8a8d797664d86b71afafec71df760066c8833f866dfd2439fcdf042f7c53bdbe6c839db67b6a3717d7ed9758dfc806337046f1cb732bcc92f2322d4bfdbd656fe5941c41d758e769b335393374faff6a7d2ecf0eb5d12ab30c986dcef".getBytes());

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = Map.of(
                "roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SECRET_KAY, SignatureAlgorithm.HS256)
                .compact();
    }

    public List<String> extractRoles(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KAY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("roles", List.class);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KAY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean isTokenExpired(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KAY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration().before(new Date());
    }
}
