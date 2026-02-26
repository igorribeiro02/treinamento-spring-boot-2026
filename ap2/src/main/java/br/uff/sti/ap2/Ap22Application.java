package br.uff.sti.ap2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ap22Application {

	public static void main(String[] args) {
		try(var context = SpringApplication.run(Ap22Application.class, args)){
			context.getBean(ImprimeDadosPessoa.class).imprimePessoa();
		}
	}

}
