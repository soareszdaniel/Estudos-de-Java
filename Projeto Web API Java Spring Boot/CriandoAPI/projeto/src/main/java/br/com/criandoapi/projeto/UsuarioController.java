package br.com.criandoapi.projeto;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 *   <li>POST /usuarios/login - Valida a senha de um usuário</li>
 * </ul>
 *
 * <p>Permite acesso CORS de qualquer origem.</p>
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

    // Camada de serviço para regras de negócio relacionadas a usuários
    private final UsuarioService usuarioService;

    /**
     * Construtor para injeção de dependência do serviço.
     *
     * param usuarioService Serviço contendo a lógica de negócio para usuários.
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
     * return ResponseEntity com lista de usuários e status HTTP 200 (OK).
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
     * param usuario Dados do novo usuário (ID será gerado automaticamente).
     * return ResponseEntity com o usuário criado e status HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario) {
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
     * param usuario Dados do usuário com ID obrigatório.
     * return ResponseEntity com o usuário atualizado/criado e status HTTP 201 (Created).
     */
    @PutMapping
    public ResponseEntity<Usuario> editarUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(usuarioService.editarUsuario(usuario));
    }

    /**
     * Remove um usuário pelo ID especificado na URL.
     *
     * <p><b>Exemplo de requisição:</b></p>
     * <pre>DELETE /usuarios/1</pre>
     *
     * param id ID do usuário a ser removido.
     * return ResponseEntity vazia com status HTTP 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Integer id) {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.status(204).build();
    }

    /**
     * Valida a senha de um usuário.
     *
     * <p><b>Exemplo de requisição:</b></p>
     * <pre>
     * {
     *   "email": "joao@email.com",
     *   "senha": "senha123"
     * }
     * </pre>
     *
     * param usuario Objeto contendo email e senha do usuário.
     * return ResponseEntity com status HTTP 200 (OK) se a senha for válida,
     *         ou HTTP 401 (Unauthorized) se a senha for inválida.
     */
    @PostMapping("/login")
    public ResponseEntity<Token> logar(@Valid @RequestBody UsuarioDto usuario) {
        Token token = usuarioService.gerarToken(usuario);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    /**
     * Manipula exceções de validação de argumentos de método.
     *
     * <p>Este método é acionado quando uma validação de argumento falha,
     * retornando um mapa com os campos inválidos e as mensagens de erro correspondentes.</p>
     *
     * param ex Exceção lançada quando a validação falha.
     * return Mapa contendo os nomes dos campos inválidos e as mensagens de erro.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}