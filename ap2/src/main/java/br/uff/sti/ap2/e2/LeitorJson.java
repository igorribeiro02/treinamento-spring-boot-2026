package br.uff.sti.ap2.e2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class LeitorJson { // esta classe le o JSON da pasta resources e transfoma em uma lista do java

    @Loga
    public List<Map<String, Object>> lerJson(String caminho) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // try-with-resources garantindo que o InputStream do arquivo seja fechado
        try (InputStream is = getClass().getResourceAsStream(caminho)) {
            if (is == null) {
                throw new RuntimeException("Arquivo JSON não encontrado na pasta resources!");
            }
            // Converte o JSON em uma Lista de Mapas (genérico para aceitar qualquer atributo)
            return mapper.readValue(is, new TypeReference<>() {});
        }
    }
}