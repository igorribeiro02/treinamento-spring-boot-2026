package br.uff.sti.ap2;

import org.springframework.stereotype.Component;

@Component
public class ImprimeDadosPessoa {

    private final ColecaoDePessoas colecaoDePessoas;

    public ImprimeDadosPessoa(ColecaoDePessoas colecaoDePessoas) {
        this.colecaoDePessoas = colecaoDePessoas;
    }

    @LogaTempoExecucao
    public void imprimePessoa(){
        var pessoa = colecaoDePessoas.geraPessoa();

        var str =
        """
        =========================================================
        |      Nome: %s
        |  Endereço: %s
        |     fonte: %s
        =========================================================
        """.formatted(
                pessoa.nome(),
                pessoa.endereco(),
                pessoa.fonte()
        );

        System.out.println(str);
    }
}
