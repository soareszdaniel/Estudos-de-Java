// Declaração do pacote onde a classe está localizada.
// Pacotes são usados para organizar classes em namespaces, evitando conflitos de nomes.
package com.kipper.first_spring_app.service;

// Importação da anotação @Service do Spring Framework.
// @Service é usada para indicar que a classe é um componente de serviço, ou seja, ela contém lógica de negócios.
// Essa anotação permite que o Spring detecte automaticamente a classe durante a varredura de componentes.
import org.springframework.stereotype.Service;

// Anotação que indica que esta classe é um serviço gerenciado pelo Spring.
// @Service é um tipo especial de @Component, usado para classes que implementam lógica de negócios.
// O Spring cria uma instância única (singleton) desta classe e a gerencia em seu contexto de aplicação.
@Service
public class HelloWorldService {

    // Método que retorna uma mensagem personalizada com base no nome fornecido.
    // Este método pode ser chamado por outros componentes da aplicação, como controladores.
    // Parâmetro:
    // - name: Uma string que representa o nome a ser incluído na mensagem.
    // Retorno:
    // - Uma string que contém a mensagem "Hello World" concatenada com o nome fornecido.
    public String helloWorld(String name) {
        return "Hello World " + name;
    }

}