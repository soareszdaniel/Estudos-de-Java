package br.com.criandoapi.projeto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

/**
 * Classe utilitária para manipulação de tokens JWT (JSON Web Tokens).
 * Esta classe fornece métodos para criar, validar e extrair informações de tokens JWT.
 * É usada em conjunto com Spring Security para autenticação e autorização.
 */
public class TokenUtil {

    // Constantes para configuração do token
    private static final String HEADER = "Authorization"; // Cabeçalho HTTP onde o token é enviado
    private static final String PREFIX = "Bearer "; // Prefixo do token no cabeçalho
    private static final long EXPIRATION = 12 * 60 * 60 * 1000; // Tempo de expiração do token (12 horas)
    private static final String SECRET_KEY = "MyK3Yt0T0k3nP4r@S3CuRiTY@Sp3c14L"; // Chave secreta para assinar o token
    private static final String EMISSOR = "DevNice"; // Emissor do token

    /**
     * Cria um token JWT para um usuário.
     *
     * param usuario O objeto Usuario contendo as informações do usuário.
     * return Uma string representando o token JWT no formato "Bearer <token>".
     */
    public static String createToken(Usuario usuario) {
        // Gera uma chave secreta a partir da string SECRET_KEY
        Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        // Constrói o token JWT
        String token = Jwts.builder()
                .setSubject(usuario.getNome()) // Define o assunto (normalmente o nome do usuário)
                .setIssuer(EMISSOR) // Define o emissor do token
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) // Define a data de expiração
                .signWith(secretKey, SignatureAlgorithm.HS256) // Assina o token com a chave secreta
                .compact(); // Gera o token como uma string compacta

        // Retorna o token no formato "Bearer <token>"
        return PREFIX + token;
    }

    /**
     * Verifica se a data de expiração do token é válida.
     *
     * param expiration A data de expiração do token.
     * return true se a data de expiração for válida (não expirada), false caso contrário.
     */
    private static boolean isExpirationValid(Date expiration) {
        return expiration.after(new Date(System.currentTimeMillis())); // Verifica se a data de expiração é posterior à data atual
    }

    /**
     * Verifica se o emissor do token é válido.
     *
     * param emissor O emissor do token.
     * return true se o emissor for válido, false caso contrário.
     */
    private static boolean isEmissorValid(String emissor) {
        return emissor.equals(EMISSOR); // Verifica se o emissor é o mesmo definido na constante EMISSOR
    }

    /**
     * Verifica se o assunto (subject) do token é válido.
     *
     * param username O nome do usuário (subject) extraído do token.
     * return true se o nome do usuário for válido, false caso contrário.
     */
    private static boolean isSubjectValid(String username) {
        return username != null && username.length() > 0; // Verifica se o nome do usuário não é nulo e não está vazio
    }

    /**
     * Valida o token JWT presente no cabeçalho da requisição HTTP.
     * <p>
     * param request O objeto HttpServletRequest que contém o cabeçalho com o token.
     * return Um objeto UsernamePasswordAuthenticationToken se o token for válido, ou null caso contrário.
     */
    public static UsernamePasswordAuthenticationToken validate(HttpServletRequest request) {
        // Extrai o token do cabeçalho Authorization
        String token = request.getHeader(HEADER);
        // Remove o prefixo "Bearer " do token
        token = token.replace(PREFIX, "");

        // Analisa o token e extrai as reivindicações (claims)
        Jws<Claims> jwsClaims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes()) // Define a chave secreta para validar a assinatura
                .build()
                .parseClaimsJws(token); // Analisa o token e retorna as reivindicações

        // Extrai informações do token
        String username = jwsClaims.getBody().getSubject(); // Nome do usuário (subject)
        String issuer = jwsClaims.getBody().getIssuer(); // Emissor do token
        Date expira = jwsClaims.getBody().getExpiration(); // Data de expiração do token

        // Verifica se o token é válido
        if (isSubjectValid(username) && isEmissorValid(issuer) && isExpirationValid(expira)) {
            // Retorna um objeto de autenticação do Spring Security
            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        }

        // Retorna null se o token for inválido
        return null;
    }
}