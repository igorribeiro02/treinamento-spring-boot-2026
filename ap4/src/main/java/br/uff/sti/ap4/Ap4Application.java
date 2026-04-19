package br.uff.sti.ap4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ap4Application {

	public static void main(String[] args) {
		SpringApplication.run(Ap4Application.class, args);
	}

	@Bean
	public CommandLineRunner run(Ap4Service ap4Service) {
		return args -> {
			// Parte 1: Inserção de 30 posts para 5 usuários
			ap4Service.inserir30PostsAleatorios();

			// Parte 2: Consultas Customizadas e Impressão
			ap4Service.executarConsultasCustomizadas();

			// Parte 3: Otimização com Stream
			ap4Service.imprimirUltimos15PostsStream();
		};
	}

}
