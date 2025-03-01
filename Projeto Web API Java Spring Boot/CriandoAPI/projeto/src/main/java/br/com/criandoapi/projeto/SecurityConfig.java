package br.com.criandoapi.projeto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Classe de configuração de segurança para a aplicação Spring Boot.
 * Esta classe define as regras de segurança, como autorização de endpoints,
 * desabilitação de CSRF, e configuração de CORS.
 */
@Configuration // Indica que esta classe é uma classe de configuração do Spring
@EnableWebSecurity // Habilita a configuração de segurança web no Spring Security
public class SecurityConfig {

    /**
     * Configura as regras de segurança para as requisições HTTP.
     *
     * param http Objeto HttpSecurity usado para configurar as regras de segurança.
     * return SecurityFilterChain que define a cadeia de filtros de segurança.
     * throws Exception Se ocorrer um erro durante a configuração.
     */
    @Bean // Indica que este método retorna um bean gerenciado pelo Spring
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desabilita a proteção CSRF (Cross-Site Request Forgery)
                .authorizeHttpRequests() // Inicia a configuração das regras de autorização
                .requestMatchers(HttpMethod.GET, "/usuarios").permitAll() // Permite acesso público ao endpoint GET /usuarios
                .anyRequest().authenticated() // Exige autenticação para qualquer outra requisição
                .and()
                .cors(); // Habilita o CORS (Cross-Origin Resource Sharing)

        // Adiciona um filtro personalizado antes do filtro de autenticação de usuário e senha
        http.addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Constrói e retorna a cadeia de filtros de segurança
    }
}