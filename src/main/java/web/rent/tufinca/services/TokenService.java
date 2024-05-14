package web.rent.tufinca.services;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import web.rent.tufinca.dtos.UserDTO;

@Service
public class TokenService {

    private long jwtExpiration = 99999999;
    private Key jwtKey = Keys.secretKeyFor(SignatureAlgorithm.HS512); 

    public String generarToken(UserDTO usuario) {

        ObjectMapper objectMapper = new ObjectMapper();
        String username = "";
        try {
            username = objectMapper.writeValueAsString(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println(username  );

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("authorities", authorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .signWith(jwtKey, SignatureAlgorithm.HS512) // Use your appropriate signing algorithm
                .compact();
    }

    public String getUsername(String jwtToken){
        return decodificarToken(jwtToken).getSubject();
    }

    public Date getFechaExpiracion(String jwtToken){
        return decodificarToken(jwtToken).getExpiration();
    }

    public Claims decodificarToken(String jwtToken) {

        return Jwts.parserBuilder()
                            .setSigningKey(jwtKey)
                            .build()
                            .parseClaimsJws(jwtToken)
                            .getBody();
    }
    
}
