package ma.crm.carental.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import ma.crm.carental.conf.KeycloakJwtAuthenticationConverter;



@Configuration
@EnableWebSecurity
public class SecurityConf {
    
    private final CorsConfigurationSource corsConfigurationSource ;

    private static final String[] AUTH_WHITELIST = {
        // -- Swagger UI v2
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/swagger-ui.html",
        "/webjars/**",
        // -- Swagger UI v3 (OpenAPI)
        "/v3/api-docs/**",
        "/swagger-ui/**"
        // other public endpoints of your API may be appended to this array
    };

    @Autowired
    SecurityConf(CorsConfigurationSource corsConfigurationSource) {
        this.corsConfigurationSource = corsConfigurationSource ;
    }

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
        
        http
        .cors(cors -> cors.configurationSource(corsConfigurationSource))
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests( authorize  ->  {
                authorize.requestMatchers(AUTH_WHITELIST).permitAll() ;
                authorize.requestMatchers("/**").hasRole("USER") ;
                authorize.anyRequest().authenticated() ;
            }
        )
        .oauth2ResourceServer( oauth2 ->
            oauth2.jwt( jwt ->
                jwt.jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter())
            )
        )
        .sessionManagement(sessionManagement -> 
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        
        ;

        return http.build() ;
    }

    // @Bean
    // public JwtDecoder jwtDecoder() {
    //     return JwtDecoders.fromIssuerLocation("http://localhost:8081/realms/realm1");
    // }

}