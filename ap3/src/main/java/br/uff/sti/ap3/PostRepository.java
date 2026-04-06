package br.uff.sti.ap3;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;

public interface
PostRepository extends CrudRepository<Post, Long> {

    // 2. Retorne os últimos 5 posts de um usuário específico (ordenado pela data mais recente).
    List<Post> findTop5ByUsuarioIdOrderByDataPostagemDesc(Long usuarioId);

    // 3. Retorne os últimos 10 posts que possuam a tag "rpg".
    @Query("SELECT p.* FROM ap3.post p JOIN ap3.post_tag pt ON p.id = pt.post_id WHERE pt.nome = :tagName ORDER BY p.data_postagem DESC LIMIT 10")
    List<Post> findLast10ByTagName(String tagName);

    // 4. Busque todos os posts que contenham a palavra "orc" na mensagem (LIKE '%orc%').
    List<Post> findByMensagemContaining(String termo);

    // 5. Últimos 15 posts da base utilizando Stream<Post>
    @Query("SELECT * FROM ap3.post ORDER BY data_postagem DESC LIMIT 15")
    Stream<Post> findLast15AsStream();
}
