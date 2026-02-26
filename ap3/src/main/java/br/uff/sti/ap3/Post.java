package br.uff.sti.ap3;

import lombok.With;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@With
public record Post(
        @Id Long id,
        LocalDateTime data,
        String mensagem,
        Long usuarioId,
        List<Tag> tags
) {
}
