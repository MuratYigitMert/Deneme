package HW3.Deneme.Auth;

import HW3.Deneme.Entity.User;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtUtil {

    private final String secret_key = "mysecretkey";
    private final JwtParser jwtParser;

    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("Username", user.getUsername());
        claims.put("authorities", List.of(user.getRole().getName()));

        Date tokenCreateTime = new Date();
        long accessTokenValidity = 60 * 60 * 1000; // 1 hour in milliseconds
        Date tokenExpiry = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(tokenCreateTime)
                .setExpiration(tokenExpiry)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();

        log.info("Generated JWT for user with email: {} (expires at: {})", user.getEmail(), tokenExpiry);
        log.info("Token claims: {}", claims);
        return token;
    }

    private Claims parseJwtClaims(String token) {
        log.debug("Parsing JWT token...");
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                log.debug("Token extracted from request: {}", token);
                return parseJwtClaims(token);
            } else {
                log.warn("No JWT token found in Authorization header.");
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            log.warn("JWT token is expired: {}", ex.getMessage());
            throw ex;
        } catch (JwtException | IllegalArgumentException ex) {
            req.setAttribute("invalid", ex.getMessage());
            log.error("JWT token is invalid: {}", ex.getMessage());
            throw ex;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        final String TOKEN_HEADER = "Authorization";
        final String TOKEN_PREFIX = "Bearer ";

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }

        log.debug("Authorization header is missing or doesn't start with Bearer.");
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        boolean isValid = claims.getExpiration().after(new Date());
        if (isValid) {
            log.debug("JWT token is valid (expires at: {})", claims.getExpiration());
        } else {
            log.warn("JWT token is expired (expired at: {})", claims.getExpiration());
        }
        return isValid;
    }

    public String getEmail(Claims claims) {
        String email = claims.getSubject();
        log.debug("Extracted subject (email) from claims: {}", email);
        return email;
    }
}
