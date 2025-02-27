package br.com.criandoapi.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para manipulação de operações CRUD da entidade Usuario.
 * Expõe endpoints para criação, leitura, atualização e exclusão de usuários.
 *
 * <p>Endpoints disponíveis:</p>
 * <ul>
 *   <li>GET /usuarios - Lista todos os usuários</li>
 *   <li>POST /usuarios - Cria um novo usuário</li>
 *   <li>PUT /usuarios - Atualiza ou cria um usuário (com ID especificado)</li>
 *   <li>DELETE /usuarios/{id} - Exclui um usuário pelo ID</li>
 * </ul>
 *
 * <p>Permite acesso CORS de qualquer origem.</p>
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuario dao; // Repositório direto para operações de banco de dados

    private final UsuarioService usuarioService; // Camada de serviço para regras de negócio

    /**
     * Construtor para injeção de dependência do serviço.
     *
     * param usuarioService Serviço contendo a lógica de negócio para usuários
     */
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Recupera todos os usuários cadastrados.
     *
     * <p><b>Exemplo de resposta:</b></p>
     * <pre>
     * [
     *   {
     *     "id": 1,
     *     "nome": "João Silva",
     *     "email": "joao@email.com",
     *     "senha": "senha123",
     *     "telefone": "(11) 99999-9999"
     *   }
     * ]
     * </pre>
     *
     * return ResponseEntity com lista de usuários e status HTTP 200
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> listaUsuarios() {
        return ResponseEntity.status(200).body(usuarioService.listarUsuario());
    }

    /**
     * Cria um novo usuário com os dados fornecidos.
     *
     * <p><b>Exemplo de requisição:</b></p>
     * <pre>
     * {
     *   "nome": "Carlos Oliveira",
     *   "email": "carlos@email.com",
     *   "senha": "senha789",
     *   "telefone": "(11) 77777-7777"
     * }
     * </pre>
     *
     * param usuario Dados do novo usuário (ID será gerado automaticamente)
     * @return ResponseEntity com usuário criado e status HTTP 201
     */
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(usuarioService.criarUsuario(usuario));
    }

    /**
     * Atualiza um usuário existente ou cria um novo se o ID não existir.
     *
     * <p><b>Exemplo de atualização:</b></p>
     * <pre>
     * {
     *   "id": 1,
     *   "nome": "João Silva Atualizado",
     *   "email": "joao.novo@email.com",
     *   "telefone": "(11) 99999-0000"
     * }
     * </pre>
     *
     * <p><b>Exemplo de criação com ID específico:</b></p>
     * <pre>
     * {
     *   "id": 999,
     *   "nome": "Usuário Teste",
     *   "email": "teste@email.com",
     *   "senha": "teste123",
     *   "telefone": "(11) 12345-6789"
     * }
     * </pre>
     *
     * param usuario Dados do usuário com ID obrigatório
     * return ResponseEntity com usuário atualizado/criado e status HTTP 201
     */
    @PutMapping
    public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(dao.save(usuario));
    }

    /**
     * Remove um usuário pelo ID especificado na URL.
     *
     * <p><b>Exemplo de requisição:</b></p>
     * <pre>DELETE /usuarios/1</pre>
     *
     * param id ID do usuário a ser removido
     * return ResponseEntity vazia com status HTTP 204
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Integer id) {
        dao.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}