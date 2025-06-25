package HW3.Deneme.Auth;


import HW3.Deneme.Entity.User;
import HW3.Deneme.Repository.UserRepo;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final UserRepo userRepo;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.startsWith("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("JWT Filter triggered for URI: " + request.getRequestURI());
        Claims claims;
        try {
            claims = jwtUtil.resolveClaims(request);
            System.out.println("Claims extracted: " + claims);
        } catch (Exception ex) {
            System.out.println("Token invalid or missing: " + ex.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        if (claims != null && jwtUtil.validateClaims(claims)) {
            String email = jwtUtil.getEmail(claims);
            System.out.println("Extracted email: " + email);

            User user = userRepo.findByEmail(email).orElse(null);
            if (user == null) {
                System.out.println("No user found with email: " + email);
            } else {
                System.out.println("User found: " + user.getUsername() + ", Role: " + user.getRole().getName());

                var authorities = List.of(new SimpleGrantedAuthority(user.getRole().getName()));
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(user, null, authorities);

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

                System.out.println("Authentication set in context with authorities: " + authorities);
            }
        } else {
            System.out.println("Claims are invalid or expired.");
        }

        filterChain.doFilter(request, response);
    }

}