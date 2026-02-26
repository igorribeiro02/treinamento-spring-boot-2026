package br.uff.sti.ap2;

import net.datafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class ColecaoDePessoas {

    record Pessoa(

            String nome,
            String endereco,
            String fonte
    ){}


    public Pessoa geraPessoa(){
        return new Pessoa(
                randomNome(),
                randomEndereco(),
                randomFonte()
        );
    }

    private String randomNome() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    private String randomEndereco(){
        Faker faker = new Faker();
        return faker.address().fullAddress();
    }

    private String randomFonte(){
        Faker faker = new Faker();
        return faker.app().author() + faker.app().version();
    }
}
