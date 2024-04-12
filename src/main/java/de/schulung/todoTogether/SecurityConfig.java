package de.schulung.todoTogether;

import java.security.AuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import de.schulung.todoTogether.Controller.MyUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    //Authorisation /Was darf der Benutzer?
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    //endpoint definieren
                    registry.requestMatchers("/", "/register/user").permitAll();
                    registry.requestMatchers("/todo").hasAnyRole("USER");
                    registry.requestMatchers("/admin").hasAnyRole("ADMIN");
                    registry.anyRequest().authenticated();
                })
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .build();
    }
    @Autowired
    MyUserService userDetailService;
    
    @Bean
    public UserDetailsService detailsService(){
        return userDetailService;
    }
    @Bean
    public AuthenticationProvider authentificationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    //Wie sind unsere Passwörter verschlüsselt?
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Authentifizierung / Wer bist du?
    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails normalUser = User.builder()
    //                                 .username("nu")
    //                                 .password("$2a$12$Gm4OXaFwnqJUG622vP1SbOHbDBoDYBX.qYhILSJ7Sku3QlXur6D1.")
    //                                 .roles("USER")
    //                                 .build();

    //     UserDetails adminUser = User.builder()
    //                                 .username("admin")
    //                                 .password("$2a$12$Gm4OXaFwnqJUG622vP1SbOHbDBoDYBX.qYhILSJ7Sku3QlXur6D1.")
    //                                 .roles("USER", "ADMIN")
    //                                 .build();

    //     return new InMemoryUserDetailsManager(normalUser, adminUser);
    // }



}
