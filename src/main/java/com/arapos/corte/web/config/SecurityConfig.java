package com.arapos.corte.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        /* --------------------------------------------------------
                                            AUTH CONTROLLER -- LOGIN
                        --------------------------------------------------------- */
                        .requestMatchers("/auth/**").permitAll()

                        /* --------------------------------------------------------
                                            CATEGORIES CONTROLLER
                        --------------------------------------------------------- */
                        .requestMatchers(HttpMethod.GET, "/categories/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR", "VIEWER")
                        .requestMatchers(HttpMethod.POST, "/categories/*").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR")
                        .requestMatchers(HttpMethod.PUT , "/categories/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE , "/categories/*").hasAnyRole("SUPER_ADMIN")

                        /* --------------------------------------------------------
                                            CLOTHS CONTROLLER
                        --------------------------------------------------------- */
                        .requestMatchers(HttpMethod.GET, "/cloths/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR", "VIEWER")
                        .requestMatchers(HttpMethod.POST, "/cloths/*").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR")
                        .requestMatchers(HttpMethod.PUT , "/cloths/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE , "/cloths/*").hasAnyRole("SUPER_ADMIN")

                        /* --------------------------------------------------------
                                            CLOTHS ENTRY CONTROLLER
                        --------------------------------------------------------- */
                        .requestMatchers(HttpMethod.GET, "/cloths_entry/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR", "VIEWER")
                        .requestMatchers(HttpMethod.POST, "/cloths_entry/*").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR")
                        .requestMatchers(HttpMethod.PUT , "/cloths_entry/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE , "/cloths_entry/*").hasAnyRole("SUPER_ADMIN")

                        /* --------------------------------------------------------
                                            CLOTHS ENTRY ITEM CONTROLLER
                        --------------------------------------------------------- */
                        .requestMatchers(HttpMethod.GET, "/cloths_entry_item/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR", "VIEWER")
                        .requestMatchers(HttpMethod.POST, "/cloths_entry_item/*").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR")
                        .requestMatchers(HttpMethod.PUT , "/cloths_entry_item/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE , "/cloths_entry_item/*").hasAnyRole("SUPER_ADMIN")

                        /* --------------------------------------------------------
                                            FULL OP CONTROLLER
                        --------------------------------------------------------- */
                        .requestMatchers(HttpMethod.POST, "/full-op/*").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR")

                        /* --------------------------------------------------------
                                            ITEM CLOTHS CONTROLLER
                        --------------------------------------------------------- */
                        .requestMatchers(HttpMethod.GET, "/item-cloths/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR", "VIEWER")
                        .requestMatchers(HttpMethod.PUT , "/item-cloths/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE , "/item-cloths/*").hasAnyRole("SUPER_ADMIN")

                        /* --------------------------------------------------------
                                            ITEM REFERENCES CONTROLLER
                        --------------------------------------------------------- */
                        .requestMatchers(HttpMethod.GET, "/item-references/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR", "VIEWER")
                        .requestMatchers(HttpMethod.PUT , "/item-references/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE , "/item-references/*").hasAnyRole("SUPER_ADMIN")

                        /* --------------------------------------------------------
                                            OPS CONTROLLER
                        --------------------------------------------------------- */
                        .requestMatchers(HttpMethod.GET, "/ops/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR", "VIEWER")
                        .requestMatchers(HttpMethod.PUT , "/ops/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE , "/ops/*").hasAnyRole("SUPER_ADMIN")

                        /* --------------------------------------------------------
                                            REFERENCES CONTROLLER
                        --------------------------------------------------------- */
                        .requestMatchers(HttpMethod.GET, "/references/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR", "VIEWER")
                        .requestMatchers(HttpMethod.POST, "/references/*").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR")
                        .requestMatchers(HttpMethod.PUT , "/references/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE , "/references/*").hasAnyRole("SUPER_ADMIN")

                        /* --------------------------------------------------------
                                            SUPPLIERS CONTROLLER
                        --------------------------------------------------------- */
                        .requestMatchers(HttpMethod.GET, "/suppliers/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR", "VIEWER")
                        .requestMatchers(HttpMethod.POST, "/suppliers/*").hasAnyRole("SUPER_ADMIN", "ADMIN", "EDITOR")
                        .requestMatchers(HttpMethod.PUT , "/suppliers/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE , "/suppliers/*").hasAnyRole("SUPER_ADMIN")

                        /* --------------------------------------------------------
                                            USERS CONTROLLER
                        --------------------------------------------------------- */
                        .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("SUPER_ADMIN", "ADMIN", "VIEWER")
                        .requestMatchers(HttpMethod.POST, "/users/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.PUT , "/users/*").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE , "/users/*").hasAnyRole("SUPER_ADMIN")

                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
