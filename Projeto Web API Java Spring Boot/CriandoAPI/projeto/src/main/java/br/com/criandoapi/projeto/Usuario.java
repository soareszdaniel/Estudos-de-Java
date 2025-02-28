// Declaração do pacote onde a classe está localizada.
package br.com.criandoapi.projeto;

// Importações necessárias para o uso de anotações JPA e Lombok.
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa a entidade "Usuario" no sistema.
 * Esta classe é mapeada para a tabela "usuarios" no banco de dados.
 * Contém informações básicas de cadastro e autenticação de usuários.
 */
@Data // Anotação do Lombok para gerar automaticamente getters, setters, toString, equals e hashCode.
@Entity // Indica que esta classe é uma entidade JPA e será mapeada para uma tabela no banco de dados.
@Table(name = "usuarios") // Especifica o nome da tabela no banco de dados.
public class Usuario {

    /**
     * Identificador único do usuário (chave primária).
     * Gerado automaticamente pelo banco de dados com auto-incremento.
     */
    @Id // Indica que este campo é a chave primária da entidade.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia de geração de valor para a chave primária (auto-incremento).
    @Column(name = "id") // Mapeia o campo para a coluna "id" na tabela.
    private int id;

    /**
     * Versão do registro, usada para controle de concorrência.
     */
    @Version // Anotação para controle de concorrência otimista.
    @Column(name = "version") // Mapeia o campo para a coluna "version" na tabela.
    private int version;

    /**
     * Nome completo do usuário.
     * Tamanho máximo: 200 caracteres.
     * Pode ser nulo.
     */
    @Column(name = "nome", length = 200, nullable = true) // Mapeia o campo para a coluna "nome" na tabela, com tamanho máximo de 200 caracteres e permitindo valores nulos.
    private String nome;

    /**
     * Endereço de e-mail do usuário.
     * Tamanho máximo: 50 caracteres.
     * Pode ser nulo.
     */
    @Column(name = "email", length = 50, nullable = true) // Mapeia o campo para a coluna "email" na tabela, com tamanho máximo de 50 caracteres e permitindo valores nulos.
    private String email;

    /**
     * Senha do usuário.
     * Armazenada como texto no banco de dados.
     * Pode ser nulo.
     * Observação: Em um cenário real, a senha deve ser criptografada.
     */
    @Column(name = "senha", columnDefinition = "TEXT", nullable = true) // Mapeia o campo para a coluna "senha" na tabela, armazenada como texto e permitindo valores nulos.
    private String senha;

    /**
     * Número de telefone do usuário.
     * Tamanho máximo: 15 caracteres.
     * Pode ser nulo.
     */
    @Column(name = "telefone", length = 15, nullable = true) // Mapeia o campo para a coluna "telefone" na tabela, com tamanho máximo de 15 caracteres e permitindo valores nulos.
    private String telefone;

}