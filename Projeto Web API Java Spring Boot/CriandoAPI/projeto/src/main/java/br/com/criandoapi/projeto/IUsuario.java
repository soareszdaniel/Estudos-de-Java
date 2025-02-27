package br.com.criandoapi.projeto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface que estende `CrudRepository` para operações de banco de dados relacionadas à entidade `Usuario`.
 * Esta interface fornece métodos prontos para operações CRUD (Create, Read, Update, Delete) sem a necessidade de implementação manual.
 * param <Usuario> Tipo da entidade gerenciada por este repositório.
 * param <Integer> Tipo da chave primária da entidade `Usuario`.
 */
public interface IUsuario extends JpaRepository<Usuario, Integer> {

    /**
     * Herda métodos CRUD básicos do `CrudRepository`, como:
     * - `save(Usuario entity)`: Salva ou atualiza uma entidade.
     * - `findById(Integer id)`: Busca uma entidade pelo ID.
     * - `findAll()`: Retorna todas as entidades.
     * - `deleteById(Integer id)`: Remove uma entidade pelo ID.
     * - `count()`: Retorna o número total de entidades.
     *
     * Além disso, é possível adicionar métodos personalizados para consultas específicas, seguindo as convenções do Spring Data JPA.
     * Exemplo:
     * - `findByNome(String nome)`: Busca usuários pelo nome.
     * - `findByEmail(String email)`: Busca usuários pelo e-mail.
     */
}