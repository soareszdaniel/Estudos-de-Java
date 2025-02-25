package br.com.criandoapi.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para manipulação de recursos relacionados à entidade `Usuario`.
 * Esta classe expõe endpoints HTTP para operações de CRUD (Create, Read, Update, Delete) de usuários.
 *
 * Endpoints:
 * - GET /usuarios: Retorna uma lista de todos os usuários.
 * - POST /usuarios: Cria um novo usuário.
 * - PUT /usuarios: Atualiza um usuário existente.
 * - DELETE /usuarios/{id}: Exclui um usuário pelo ID.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuario dao; // Repositório para operações de banco de dados relacionadas à entidade Usuario.

    /**
     * Retorna uma lista de todos os usuários cadastrados no sistema.
     *
     * Método HTTP: GET
     * Caminho: /usuarios
     *
     * @return List<Usuario> - Lista de usuários cadastrados.
     *
     * Exemplo de resposta:
     * [
     *   {
     *     "id": 1,
     *     "nome": "João Silva",
     *     "email": "joao@email.com",
     *     "senha": "senha123",
     *     "telefone": "(11) 99999-9999"
     *   },
     *   {
     *     "id": 2,
     *     "nome": "Maria Souza",
     *     "email": "maria@email.com",
     *     "senha": "senha456",
     *     "telefone": "(11) 88888-8888"
     *   }
     * ]
     */
    @GetMapping
    public List<Usuario> listaUsuarios() {
        return (List<Usuario>) dao.findAll();
    }

    /**
     * Cria um novo usuário com os dados fornecidos no corpo da requisição.
     *
     * Método HTTP: POST
     * Caminho: /usuarios
     *
     * @param usuario Objeto `Usuario` contendo os dados do novo usuário.
     * @return Usuario - O usuário criado com o ID gerado.
     *
     * Exemplo de requisição:
     * {
     *   "nome": "Carlos Oliveira",
     *   "email": "carlos@email.com",
     *   "senha": "senha789",
     *   "telefone": "(11) 77777-7777"
     * }
     */
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioNovo = dao.save(usuario);
        return usuarioNovo;
    }

    /**
     * Atualiza um usuário existente com os dados fornecidos no corpo da requisição.
     * Método HTTP: PUT
     * Caminho: /usuarios
     *
     * @param usuario Objeto `Usuario` contendo os dados atualizados.
     * @return Usuario - O usuário atualizado.
     * Exemplo de requisição:
     * {
     *   "id": 1,
     *   "nome": "João Silva Atualizado",
     *   "email": "joao.novo@email.com",
     *   "senha": "senha123",
     *   "telefone": "(11) 99999-9999"
     * }
     */
    @PutMapping
    public Usuario editarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioNovo = dao.save(usuario);
        return usuarioNovo;
    }

    /**
     * Exclui um usuário pelo ID fornecido.
     * Método HTTP: DELETE
     * Caminho: /usuarios/{id}
     *
     * @param id ID do usuário a ser excluído.
     * @return Usuario - O usuário excluído ou `null` se o usuário não for encontrado.
     * Exemplo de requisição:
     * DELETE /usuarios/1
     */
    @DeleteMapping("/{id}")
    public Usuario excluirUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuario = dao.findById(id);
        if (usuario.isPresent()) {
            dao.deleteById(id);
            return usuario.get();
        }
        return null; // Retorna null se o usuário não for encontrado.
    }
}