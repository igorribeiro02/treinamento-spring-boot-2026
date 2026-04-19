package br.uff.sti.ap4;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UsuarioDAO usuarioDAO;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post findObjById(long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post com id %d não encontrado".formatted(id)));
    }

    @Transactional(readOnly = true)
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public record PostComUsuario(
            @Delegate Post post,
            Usuario usuario
    ) {}

    @Transactional(readOnly = true)
    public PostComUsuario findObyComUsuarioById(long id) {
        val post = findObjById(id);
        val usuario = usuarioDAO.findById(post.usuarioId()).orElseThrow();
        return new PostComUsuario(post, usuario);
    }
}
