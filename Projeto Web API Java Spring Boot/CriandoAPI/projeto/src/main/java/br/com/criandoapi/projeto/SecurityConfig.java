package br.com.criandoapi.projeto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desabilita a proteção CSRF
                .authorizeHttpRequests() // Define as regras de autorização
                .requestMatchers(HttpMethod.GET, "/usuarios").permitAll() // Permite acesso público ao endpoint GET /usuarios
                .anyRequest().authenticated() // Exige autenticação para qualquer outra requisição
                .and()
                .cors(); // Habilita o CORS

        http.addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}