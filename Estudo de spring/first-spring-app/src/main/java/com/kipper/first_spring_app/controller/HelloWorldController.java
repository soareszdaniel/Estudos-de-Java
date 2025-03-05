// Declaração do pacote onde a classe está localizada.
// Pacotes são usados para organizar classes em namespaces, evitando conflitos de nomes.
package com.kipper.first_spring_app.controller;

// Importações das anotações e classes do Spring Framework usadas para criar um controlador REST.
// - RestController: Indica que a classe é um controlador REST, onde cada método retorna um objeto que é serializado diretamente para o corpo da resposta HTTP.
// - RequestMapping: Mapeia URLs para métodos do controlador.
// - GetMapping: Especifica que um método deve ser mapeado para requisições HTTP GET.
// - PostMapping: Especifica que um método deve ser mapeado para requisições HTTP POST.
// - RequestBody: Indica que o corpo da requisição HTTP deve ser vinculado a um objeto.
// - PathVariable: Extrai valores de variáveis presentes no caminho da URL.
// - RequestParam: Extrai valores de parâmetros de consulta (query parameters) da URL.
// - Autowired: Anotação usada para injetar dependências automaticamente.
// - HelloWorldService: Serviço injetado para encapsular a lógica de negócios relacionada à mensagem "Hello World".
// - User: Classe de domínio que representa um usuário.
import com.kipper.first_spring_app.domain.User;
import com.kipper.first_spring_app.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Anotação que indica que esta classe é um controlador REST.
// @RestController combina @Controller e @ResponseBody, o que significa que os métodos retornam dados diretamente no corpo da resposta HTTP.
@RestController
// Anotação que mapeia todas as requisições com o caminho "/hello-world" para este controlador.
// Qualquer método dentro desta classe será acessível através do caminho base "/hello-world".
@RequestMapping("/hello-world")
public class HelloWorldController {

    // Injeção de dependência do serviço HelloWorldService.
    // A anotação @Autowired informa ao Spring para injetar automaticamente uma instância de HelloWorldService aqui.
    // Isso permite que o controlador use a lógica de negócios encapsulada no serviço.
    @Autowired
    private HelloWorldService helloWorldService;

    // Anotação que mapeia requisições HTTP GET para o método helloWorld().
    // Como não foi especificado um caminho adicional, este método responde a requisições no caminho base "/hello-world".
    @GetMapping
    // Método que retorna uma mensagem personalizada "Hello World [nome]".
    // O valor retornado será serializado diretamente no corpo da resposta HTTP.
    // Neste exemplo, o nome "Daniel" é passado como argumento para o serviço.
    public String helloWorld() {
        return helloWorldService.helloWorld("Daniel");
    }

    // Anotação que mapeia requisições HTTP POST para o método helloWorldPost().
    // Este método responde a requisições no caminho "/hello-world/{id}", onde {id} é uma variável de caminho.
    @PostMapping("/{id}")
    // Método que recebe:
    // - Um valor de caminho (PathVariable) chamado "id".
    // - Um parâmetro de consulta (RequestParam) chamado "filter", com um valor padrão "nenhum" caso não seja fornecido.
    // - Um objeto User no corpo da requisição HTTP POST.
    // A anotação @RequestBody indica que o corpo da requisição será convertido automaticamente em um objeto User.
    // O método retorna uma mensagem personalizada "Hello World [valor do filtro]".
    public String helloWorldPost(
            @PathVariable("id") String id, // Extrai o valor de {id} da URL.
            @RequestParam(value = "filter", defaultValue = "nenhum") String filter, // Extrai o parâmetro de consulta "filter".
            @RequestBody User body // Converte o corpo da requisição em um objeto User.
    ) {
        return "Hello World " + filter;
    }

}