package pb.auctionservice.config.security;

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
    public AuthenticationWebFilter authenticationWebFilter() {
        var authWebFilter = new AuthenticationWebFilter(jwtAuthenticationManager);
        authWebFilter.setServerAuthenticationConverter(jwtAuthenticationConverter);
        return authWebFilter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .csrf(CsrfSpec::disable)
//            .authenticationManager(jwtAuthenticationManager)
            .authorizeExchange(exchanges -> exchanges
                .anyExchange().authenticated()
            ).formLogin(FormLoginSpec::disable)
            .httpBasic(HttpBasicSpec::disable)
            .build();
    }

}
