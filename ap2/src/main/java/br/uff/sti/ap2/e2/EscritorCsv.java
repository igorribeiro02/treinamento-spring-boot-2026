package br.uff.sti.ap2.e2;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.PrintWriter;

@Component // será um componenet porque ele será injetado na classe ImprimeDadosPessoa, e ele tem a responsabilidade de salvar o CSV em um arquivo
public class EscritorCsv {
    //esta classe pega a String do CSV e salva fisicamente em um arquivo na raiz do projeto

    @Loga
    public void escreverCsv(String caminhoArquivo, String conteudo) throws Exception{
        // try-with-resources garantindo que o FileWriter e PrintWriter sejam fechados corretamente
        try (FileWriter fw = new FileWriter(caminhoArquivo, true); // "true" para modo append
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(conteudo); // Escreve a linha do CSV no arquivo

        } catch (Exception e) {
            System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
            throw e; // Re-throw para que o aspecto de log possa capturar a exceção, se necessário
        }
    }

}
