package br.com.criandoapi.projeto;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de segurança personalizado que estende a classe `OncePerRequestFilter` do Spring.
 * Este filtro é executado uma vez por cada requisição e pode ser usado para adicionar lógica
 * personalizada, como validação de tokens, logging, ou outras verificações de segurança.
 */
public class SecurityFilter extends OncePerRequestFilter {

    /**
     * Método que implementa a lógica do filtro. Este método é chamado uma vez por requisição.
     *
     * param request     O objeto HttpServletRequest que contém os dados da requisição.
     * param response    O objeto HttpServletResponse que contém os dados da resposta.
     * param filterChain O objeto FilterChain que permite a execução dos próximos filtros na cadeia.
     * throws ServletException Se ocorrer um erro relacionado ao servlet.
     * throws IOException      Se ocorrer um erro de entrada/saída.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Aqui você pode adicionar lógica personalizada antes de passar a requisição para o próximo filtro.
        // Por exemplo, validar tokens JWT, adicionar cabeçalhos personalizados, ou registrar logs.

        if(request.getHeader("Authorization") != null){
            UsernamePasswordAuthenticationToken auth = TokenUtil.validate(request);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // Passa a requisição e a resposta para o próximo filtro na cadeia.
        filterChain.doFilter(request, response);

        // Aqui você pode adicionar lógica personalizada após a execução dos próximos filtros.
        // Por exemplo, registrar logs ou modificar a resposta.
    }
}