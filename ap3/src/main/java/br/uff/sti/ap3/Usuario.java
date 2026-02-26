package br.uff.sti.ap3;

import org.springframework.data.annotation.Id;

public record Usuario(
        @Id Long id,
        String username,
        String nome,
        int idade,
        String urlImagem
) {
}
