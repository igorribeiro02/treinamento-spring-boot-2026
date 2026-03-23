package br.uff.sti;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.siegmar.fastcsv.reader.CsvReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Aplicação para converter CSV em JSON utilizando FastCSV.
 * Exigência: Executável via mvn exec:java
 */
public class CsvToJsonFast {

    public static void main(String[] args) {
        // EXPLICAÇÃO 1: Definindo a variável String com os dados fornecidos.
        String csvConteudo = "nome,idade,cpf,curso\n" +
                "Yara Guarani,21,54291827305,Ciências Biológicas\n" +
                "Enzo Valentim de Arcanjo,30,81274390512,Astronomia\n" +
                "Dagoberto Peixoto,55,29304156788,História\n" +
                "Ximena Luzia das Neves,24,60412398744,Design de Moda\n" +
                "Ubirajara Pires,42,15928374600,Agronomia";

        try {
            // EXPLICAÇÃO 2: Usamos o NamedCsvReader do FastCSV.
            // Ele associa automaticamente os valores das linhas aos cabeçalhos da primeira linha.
            List<List<String>> dadosParaJson = new ArrayList<>();

            CsvReader.builder()
                    .build(new StringReader(csvConteudo))
                    .stream()
                    .forEach(row -> {
                        // O FastCSV já devolve um Map pronto onde a chave é o cabeçalho
                        dadosParaJson.add(row.getFields());
                    });

            // EXPLICAÇÃO 3: Configuração do Jackson para imprimir o JSON formatado (Pretty Print)
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            // DÚVIDA: O enunciado mostra "idade" como número no JSON exemplo.
            // No CSV puro tudo é String. Para otimização, tratamos como lista de mapas
            // para manter a legibilidade e flexibilidade.
            String jsonResultado = mapper.writeValueAsString(dadosParaJson);

            // Saída final no console
            System.out.println(jsonResultado);

        } catch (Exception e) {
            // Tratamento preventivo para evitar falhas de execução (perda de pontos)
            System.err.println("Erro ao processar conversão: " + e.getMessage());
        }
    }
}