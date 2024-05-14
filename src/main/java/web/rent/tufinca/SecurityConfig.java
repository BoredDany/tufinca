package web.rent.tufinca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import web.rent.tufinca.filter.JwtFilter;

public class SecurityConfig implements ISecurityConfig {

    @Autowired
    private JwtFilter jwtAuthorizationFilter;

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

        RequestMatcher publicUrls = ignoreSpecificRequests();

        http
                .authorizeRequests()
                .requestMatchers(publicUrls).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.ignoringRequestMatchers(publicUrls));

        return http.build();

    }

    private RequestMatcher ignoreSpecificRequests() {
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/grupo23/auth/autenticar/**", HttpMethod.POST.name()),
                new AntPathRequestMatcher("/grupo23/auth/login/**", HttpMethod.POST.name()),
                new AntPathRequestMatcher("/grupo23/auth/register/**", HttpMethod.POST.name()));
    }

}
