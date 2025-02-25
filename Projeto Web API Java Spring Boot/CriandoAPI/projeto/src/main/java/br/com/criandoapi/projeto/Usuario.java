package br.com.criandoapi.projeto;

import jakarta.persistence.*;

/**
 * Classe que representa a entidade "Usuario" no sistema.
 * Esta classe é mapeada para a tabela "usuarios" no banco de dados.
 * Contém informações básicas de cadastro e autenticação de usuários.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    /**
     * Identificador único do usuário (chave primária).
     * Gerado automaticamente pelo banco de dados com auto-incremento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    @Column(name = "version")
    private int version;

    /**
     * Nome completo do usuário.
     * Tamanho máximo: 200 caracteres.
     * Pode ser nulo.
     */
    @Column(name = "nome", length = 200, nullable = true)
    private String nome;

    /**
     * Endereço de e-mail do usuário.
     * Tamanho máximo: 50 caracteres.
     * Pode ser nulo.
     */
    @Column(name = "email", length = 50, nullable = true)
    private String email;

    /**
     * Senha do usuário.
     * Armazenada como texto no banco de dados.
     * Pode ser nulo.
     * Observação: Em um cenário real, a senha deve ser criptografada.
     */
    @Column(name = "senha", columnDefinition = "TEXT", nullable = true)
    private String senha;

    /**
     * Número de telefone do usuário.
     * Tamanho máximo: 15 caracteres.
     * Pode ser nulo.
     */
    @Column(name = "telefone", length = 15, nullable = true)
    private String telefone;

    // Métodos Getters e Setters

    /**
     * Retorna o ID do usuário.
     * @return int - ID do usuário.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do usuário.
     * @param id - ID do usuário.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome do usuário.
     * @return String - Nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do usuário.
     * @param nome - Nome do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o e-mail do usuário.
     * @return String - E-mail do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do usuário.
     * @param email - E-mail do usuário.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a senha do usuário.
     * @return String - Senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do usuário.
     * @param senha - Senha do usuário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Retorna o telefone do usuário.
     * @return String - Telefone do usuário.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do usuário.
     * @param telefone - Telefone do usuário.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}