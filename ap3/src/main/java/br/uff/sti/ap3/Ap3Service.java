package br.uff.sti.ap3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class Ap3Service {

    public static final String URL_IMAGEM_1 = "https://fastly.picsum.photos/id/872/200/200.jpg?hmac=m0AwAUFkEiEz2KW58n6a5RVkKaClHNylfppYjE3a0v4";
    private final UsuarioDAO usuarioDAO;
    private final PostService postService;

    @Transactional
    public void exercicio_a(){
        val usuario = criaUsuario();

        val post = new Post(null,
                LocalDateTime.now(),
                "Meu primeiro post!",
                usuario.id(),
                Tag.of("first", "java", "spring-boot"));

        val postSalvo = postService.save(post);

        log.info("post salvo: {}", postSalvo);
    }

    @Transactional
    public Usuario criaUsuario(){
        return usuarioDAO.save(
                new Usuario(null,
                        "1234",
                        "João Numerador",
                        27,
                        URL_IMAGEM_1)
        );
    }
}
