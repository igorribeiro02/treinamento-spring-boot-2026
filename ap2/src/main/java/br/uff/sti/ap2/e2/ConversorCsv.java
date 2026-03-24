package br.uff.sti.ap2.e2;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Component
public class ConversorCsv {

    @Loga // Anotação personalizada para logar a execução do método
    public String converterParaCsv(List<Map<String, Object>> dados) {
        if (dados == null || dados.isEmpty()) { // Verifica se a lista está vazia ou nula para evitar erros
            return ""; // Retorna uma string vazia se não houver dados para converter
        }

        StringBuilder csvFinal = new StringBuilder();

        // Pega as chaves (atributos) do primeiro item para montar o cabeçalho dinamicamente
        Map<String, Object> primeiroItem = dados.get(0);
        StringJoiner cabecalho = new StringJoiner(",");
        primeiroItem.keySet().forEach(cabecalho::add);
        csvFinal.append(cabecalho.toString()).append("\n");

        // Varre a lista preenchendo as linhas com os valores correspondentes
        for (Map<String, Object> linha : dados) {
            StringJoiner valores = new StringJoiner(",");
            for (String chave : primeiroItem.keySet()) {
                valores.add(String.valueOf(linha.get(chave)));
            }
            csvFinal.append(valores.toString()).append("\n");
        }

        return csvFinal.toString();
    }
}