package web.rent.tufinca.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import web.rent.tufinca.dtos.UserDTO;
import web.rent.tufinca.entities.User;
import web.rent.tufinca.repositories.RepositoryUser;
import web.rent.tufinca.utils.schemas.LoginSchema;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private UserService userService;

    //@Value("${jwt.secret}")
    //private String secret;

    // @Value("${jwt.expiration}")
    private long expiration = 9999999;

    private Key jwtKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    private UserDTO getUserFromBasicAuthToken(String authToken) {
        String rawToken = authToken.split(" ")[1];
        String[] decoded = new String(Base64.getDecoder().decode(rawToken), StandardCharsets.UTF_8).split(":");
        UserDTO user = userService.getByEmail(decoded[0]);

        if (user == null) return null;

        if ( !BCrypt
                .verifyer()
                .verify(
                        decoded[1].toCharArray(),
                        user.getPassword().toCharArray()
                ).verified
        ) return null;

        return user;
    }

    public String generateToken(String basicAuthHeader) {

        UserDTO user = getUserFromBasicAuthToken(basicAuthHeader);
        if (user == null) return "NOT_FOUND";
        ObjectMapper objectMapper = new ObjectMapper();
        String username = "";

        try {
            username = objectMapper.writeValueAsString(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("authorities", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                )
                .signWith(jwtKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsername(String token) {
        return decodeToken(token).getSubject();
    }

    public Date getExpiration(String token) {
        return decodeToken(token).getExpiration();
    }

    public Claims decodeToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public UserDTO register(UserDTO userDTO) {
        Optional<User> possibleUser = repositoryUser.findByEmail(userDTO.getEmail());
        if (possibleUser.isPresent()) return null;
        return userService.save(userDTO);
    }

    //
    public Long getUserIdFromToken(String token) {
        Claims claims = decodeToken(token);
        // Asume que el userId se almacena en el claim "userId"
        return claims.get("userId", Long.class);
    }
}
