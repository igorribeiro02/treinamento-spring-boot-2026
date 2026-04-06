package br.uff.sti.ap3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ap3Application {

	public static void main(String[] args) {
		try(var context = SpringApplication.run(Ap3Application.class, args)){
			var ap3Service = context.getBean(Ap3Service.class);

			// Parte 1: Inserção de 30 posts para 5 usuários
			ap3Service.inserir30PostsAleatorios();

			// Parte 2: Consultas Customizadas e Impressão
			ap3Service.executarConsultasCustomizadas();

			// Parte 3: Otimização com Stream
			ap3Service.imprimirUltimos15PostsStream();
		}
	}

}
