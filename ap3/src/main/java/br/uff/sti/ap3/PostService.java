package br.uff.sti.ap3;

import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Validator;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
public class PostService {

    private final Validator validator;

    private final PostRepository postRepository;

    @Transactional
    public Post save(Post post){
        assert post.id() == null;

        post = post.withData(LocalDateTime.now());

        final Set<ConstraintViolation<Post>> violations = validator.validate(post);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Objeto inválido: " + violations);
        }

        return postRepository.save(post);
    }
}
