package br.uff.sti.ap2;

import org.springframework.boot.SpringApplication;

//@SpringBootApplication
public class Ap21Application {

	public static void main(String[] args) {
		try(var context = SpringApplication.run(Ap21Application.class, args)){
			context.getBean(AloMundoComponent.class).aloMundo("jupiter");
		}
	}

}
