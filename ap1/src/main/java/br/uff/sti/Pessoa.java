package br.uff.sti;

import lombok.With;

@With
public record Pessoa(
        String nome,
        String cpf
) {
}
