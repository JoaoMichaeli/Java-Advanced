package br.com.fiap.fin_money_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(
                auth -> auth
//                        .requestMatchers("/categories/**")
//                        .hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()                
        )
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .build();
    }



//    @Bean
//    UserDetailsService userDetailsService(){
//
//        var users = List.of(
//                User
//                        .withUsername("joao")
//                        .password("$2a$12$FH9cPpPvuW4td6wBcr//8uw55f45mBP72Oj6PelTwco/awxZIis6a")
//                        .roles("ADMIN")
//                        .build(),
//                User
//                        .withUsername("maria")
//                        .password("$2a$12$5FQ2rvkCnOLDGb1M0DjFj.jVcJBGKBK9lJbaFe/W2NPfFvd4Hz4sm")
//                        .roles("USER")
//                        .build()
//        );
//
//        return new InMemoryUserDetailsManager(users);
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
