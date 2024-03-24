package pb.auctionservice.config.security;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.FormLoginSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import pb.auctionservice.config.security.converter.JWTAuthenticationConverter;
import pb.auctionservice.config.security.manager.JWTAuthenticationManager;

@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final JWTAuthenticationManager jwtAuthenticationManager;

    private final JWTAuthenticationConverter jwtAuthenticationConverter;

    @Autowired
    public WebSecurityConfig(JWTAuthenticationManager jwtAuthenticationManager, JWTAuthenticationConverter jwtAuthenticationConverter) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
        this.jwtAuthenticationConverter = jwtAuthenticationConverter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8081"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationWebFilter authenticationWebFilter() {
        var authWebFilter = new AuthenticationWebFilter(jwtAuthenticationManager);
        authWebFilter.setServerAuthenticationConverter(jwtAuthenticationConverter);
        return authWebFilter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .cors(corsSpec -> corsSpec.configurationSource(corsConfigurationSource()))
            .csrf(CsrfSpec::disable)
            .authorizeExchange(exchanges -> exchanges
                .anyExchange().authenticated()
            ).formLogin(FormLoginSpec::disable)
            .httpBasic(HttpBasicSpec::disable)
            .build();
    }

}
