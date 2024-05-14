package web.rent.tufinca;

import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import web.rent.tufinca.filters.JWTAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements  ISecurityConfig {

    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Override
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Override
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(
                jwtAuthFilter, UsernamePasswordAuthenticationFilter.class
        ).csrf( csrf -> csrf.ignoringRequestMatchers(ignoreSpecificRequests()));

        return http.build();
    }

    private RequestMatcher ignoreSpecificRequests() {
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/grupo23/**", "POST"),
                new AntPathRequestMatcher("/grupo23/**", "GET"),
                new AntPathRequestMatcher("/grupo23/**", "PUT"),
                new AntPathRequestMatcher("/grupo23/**", "DELETE")
        );
    }


}
