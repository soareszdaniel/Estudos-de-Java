package br.com.criandoapi.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@CrossOrigin("*") // Permite acesso de qualquer origem (CORS)
@RequestMapping("/usuarios") // Define o caminho base para todos os endpoints deste controlador
public class UsuarioController {

    @Autowired
    private IUsuario dao; // Repositório para operações de banco de dados relacionadas à entidade Usuario.

    /**
     * Retorna uma lista de todos os usuários cadastrados no sistema.
     *
     * Método HTTP: GET
     * Caminho: /usuarios
     *
     * @return ResponseEntity<List<Usuario>> - Resposta HTTP contendo a lista de usuários e o status 200 (OK).
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
    public ResponseEntity<List<Usuario>> listaUsuarios() {
        List<Usuario> lista = (List<Usuario>) dao.findAll(); // Busca todos os usuários no banco de dados
        return ResponseEntity.status(200).body(lista); // Retorna a lista com status HTTP 200 (OK)
    }

    /**
     * Cria um novo usuário com os dados fornecidos no corpo da requisição.
     *
     * Método HTTP: POST
     * Caminho: /usuarios
     *
     * @param usuario Objeto `Usuario` contendo os dados do novo usuário.
     * @return ResponseEntity<Usuario> - Resposta HTTP contendo o usuário criado e o status 201 (Created).
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
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioNovo = dao.save(usuario); // Salva o novo usuário no banco de dados
        return ResponseEntity.status(201).body(usuarioNovo); // Retorna o usuário criado com status HTTP 201 (Created)
    }

    /**
     * Atualiza um usuário existente com os dados fornecidos no corpo da requisição.
     *
     * Método HTTP: PUT
     * Caminho: /usuarios
     *
     * @param usuario Objeto `Usuario` contendo os dados atualizados.
     * @return ResponseEntity<Usuario> - Resposta HTTP contendo o usuário atualizado e o status 201 (Created).
     *
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
    public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioNovo = dao.save(usuario); // Atualiza o usuário no banco de dados
        return ResponseEntity.status(201).body(usuarioNovo); // Retorna o usuário atualizado com status HTTP 201 (Created)
    }

    /**
     * Exclui um usuário pelo ID fornecido.
     *
     * Método HTTP: DELETE
     * Caminho: /usuarios/{id}
     *
     * @param id ID do usuário a ser excluído.
     * @return ResponseEntity<?> - Resposta HTTP com status 204 (No Content) indicando que o usuário foi excluído.
     *
     * Exemplo de requisição:
     * DELETE /usuarios/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Integer id) {
        dao.deleteById(id); // Exclui o usuário pelo ID
        return ResponseEntity.status(204).build(); // Retorna status HTTP 204 (No Content)
    }
}