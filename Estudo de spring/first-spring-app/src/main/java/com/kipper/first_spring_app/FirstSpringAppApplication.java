// Declaração do pacote onde a classe está localizada.
// Pacotes são usados para organizar classes em namespaces, evitando conflitos de nomes.
package com.kipper.first_spring_app;

// Importação das classes necessárias do Spring Framework.
// SpringApplication é a classe principal usada para iniciar uma aplicação Spring.
// SpringBootApplication é uma anotação que simplifica a configuração de uma aplicação Spring Boot.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Anotação que indica que esta classe é uma aplicação Spring Boot.
// @SpringBootApplication é uma anotação de conveniência que combina:
// - @Configuration: Indica que a classe pode ser usada como uma fonte de definições de beans.
// - @EnableAutoConfiguration: Habilita a configuração automática do Spring Boot.
// - @ComponentScan: Habilita a varredura de componentes no pacote atual e subpacotes.
@SpringBootApplication
public class FirstSpringAppApplication {

	// Método principal que serve como ponto de entrada para a aplicação Java.
	// Este método é chamado quando a aplicação é iniciada.
	public static void main(String[] args) {
		// Inicia a aplicação Spring Boot.
		// SpringApplication.run() inicializa o contexto da aplicação Spring, carrega os beans,
		// e inicia o servidor embutido (se configurado).
		// FirstSpringAppApplication.class é a classe principal da aplicação.
		// args são os argumentos de linha de comando passados para a aplicação.
		SpringApplication.run(FirstSpringAppApplication.class, args);
	}

}