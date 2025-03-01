package br.com.criandoapi.projeto;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa um objeto de token.
 * Esta classe é usada para encapsular um token em um objeto, facilitando o manuseio
 * e a transferência de dados em uma aplicação.
 *
 * A anotação `Getter` do Lombok gera automaticamente o método getter para o campo `token`.
 * A anotação `Setter` do Lombok gera automaticamente o método setter para o campo `token`.
 */
@Getter // Anotação do Lombok para gerar automaticamente o método getter
@Setter // Anotação do Lombok para gerar automaticamente o método setter
public class Token {

    /**
     * Campo que armazena o valor do token.
     */
    private String token;

    /**
     * Construtor que inicializa o objeto Token com um valor de token.
     *
     * param token O valor do token a ser armazenado.
     */
    public Token(String token) {
        super(); // Chama o construtor da classe pai (neste caso, Object)
        this.token = token; // Inicializa o campo token com o valor fornecido
    }
}