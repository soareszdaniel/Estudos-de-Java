package br.com.criandoapi.projeto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável por implementar a lógica de negócio relacionada à entidade Usuario.
 * Esta classe gerencia operações como listagem, criação, edição, exclusão e validação de senha de usuários.
 *
 * <p>Utiliza um repositório (IUsuario) para interagir com o banco de dados e um codificador de senhas (BCryptPasswordEncoder)
 * para garantir a segurança das senhas dos usuários.</p>
 */
@Service
public class UsuarioService {

    // Repositório para operações de banco de dados relacionadas a usuários
    private final IUsuario repository;

    // Codificador de senhas para criptografar e validar senhas
    private final PasswordEncoder passwordEncoder;

    /**
     * Construtor para injeção de dependência do repositório e inicialização do codificador de senhas.
     *
     * param repository Repositório de usuários para operações de banco de dados.
     */
    public UsuarioService(IUsuario repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // Inicializa o codificador de senhas
    }

    /**
     * Retorna uma lista de todos os usuários cadastrados no banco de dados.
     *
     * return Lista de objetos Usuario.
     */
    public List<Usuario> listarUsuario() {
        return repository.findAll();
    }

    /**
     * Cria um novo usuário no banco de dados, criptografando a senha antes de salvar.
     *
     * param usuario Objeto Usuario contendo os dados do novo usuário.
     * return O usuário criado, com a senha criptografada.
     */
    public Usuario criarUsuario(Usuario usuario) {
        // Criptografa a senha do usuário
        String senhaCriptografada = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        // Salva o usuário no banco de dados
        return repository.save(usuario);
    }

    /**
     * Atualiza os dados de um usuário existente ou cria um novo usuário se o ID não existir.
     * A senha é criptografada antes de salvar.
     *
     * param usuario Objeto Usuario contendo os dados atualizados.
     * return O usuário atualizado ou criado, com a senha criptografada.
     */
    public Usuario editarUsuario(Usuario usuario) {
        // Criptografa a senha do usuário
        String senhaCriptografada = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        // Salva o usuário no banco de dados
        return repository.save(usuario);
    }

    /**
     * Exclui um usuário do banco de dados com base no ID fornecido.
     *
     * param id ID do usuário a ser excluído.
     * return true se o usuário foi excluído com sucesso.
     */
    public Boolean excluirUsuario(Integer id) {
        repository.deleteById(id);
        return true;
    }

    /**
     * Valida a senha de um usuário comparando a senha fornecida com a senha criptografada armazenada no banco de dados.
     *
     * param usuario Objeto Usuario contendo o ID e a senha a ser validada.
     * return true se a senha for válida, false caso contrário.
     */
    public Boolean validarSenha(Usuario usuario) {
        // Recupera a senha criptografada do banco de dados
        String senhaArmazenada = repository.getById(usuario.getId()).getSenha();

        // Compara a senha fornecida com a senha armazenada
        return passwordEncoder.matches(usuario.getSenha(), senhaArmazenada);
    }
}