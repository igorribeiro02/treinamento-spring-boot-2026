package br.uff.sti.ap2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class Ap2Application {

	public static void main(String[] args) {
		try(var context = SpringApplication.run(Ap2Application.class, args)){
			context.getBean(AloMundoComponent.class).aloMundo("jupiter");
		}
	}

}
