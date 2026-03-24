package br.uff.sti.ap2.e2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableAspectJAutoProxy
public class E2Application {

    public static void main(String[] args) {
        try(var context = SpringApplication.run(E2Application.class, args)){
            //Inversão de controle: o framework é quem chama o codigo do usuario e não o contrário. O framework é quem tem o controle do fluxo de execução do programa e não o usuário.
            LeitorJson leitor = context.getBean(LeitorJson.class);
            ConversorCsv conversor = context.getBean(ConversorCsv.class);
            EscritorCsv escritor = context.getBean(EscritorCsv.class);

            // Leitura do JSON
            try{
                // o leitor vai buscar pelo arquvio "pessoas.json" na pasta resources
                List<Map<String, Object>> dados = leitor.lerJson("/pessoas.json");
                // 2. O conversor transforma a lista em texto CSV
                String csvGerado = conversor.converterParaCsv(dados);

                // 3. O escritor salva o arquivo final
                escritor.escreverCsv("saida_pessoas.csv", csvGerado);

                System.out.println("processo concluido !");
                System.out.println(("CSV gerado:\n" + csvGerado));

            } catch(Exception e){
                System.err.println("Erro durante o processo: " + e.getMessage());
            }
        }
    }
}
