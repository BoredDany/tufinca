package web.rent.tufinca.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import web.rent.tufinca.CustomUserDetailsService;
import web.rent.tufinca.services.AuthService;

import java.io.IOException;
import java.security.SignatureException;

@Component
@Service
public class JWTAuthFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        System.out.println("-------------->>>----------->>> Filtro");
        try {
            if (existJWTToken(request)) {
                Claims claims = validateToken(request);
                if (claims.get("authorities") != null) {
                    String username = getUsername(request);
                    System.out.println(username);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, userDetails, null);
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    System.out.println("No authorities found");
                    SecurityContextHolder.clearContext();
                }
            } else {
                System.out.println("No jwt token found");
                SecurityContextHolder.clearContext();
                //if (!request.getRequestURI().startsWith("/grupo23/auth/")) throw new ServletException("JWT Token not found");
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(AUTHORIZATION_HEADER).replace(BEARER_PREFIX, "");
        return authService.decodeToken(jwtToken);
    }
    private String getUsername(HttpServletRequest request) {
        String jwtToken = request.getHeader(AUTHORIZATION_HEADER).replace(BEARER_PREFIX, "");
        return authService.getUsername(jwtToken);
    }

    private boolean existJWTToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        return !(token == null || !token.startsWith(BEARER_PREFIX));
    }

}
