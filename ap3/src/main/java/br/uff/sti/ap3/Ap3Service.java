package br.uff.sti.ap3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class Ap3Service {

    private final UsuarioDAO usuarioDAO;
    private final PostRepository postRepository;
    private final Faker faker = new Faker();

    /**
     * Parte 1: Inserção de Dados
     * Inserção transacional de 30 posts aleatórios distribuídos entre 5 usuários.
     */
    @Transactional
    public void inserir30PostsAleatorios() {
        log.info("Iniciando inserção de 30 posts aleatórios...");

        // Criando 5 usuários diferentes
        List<Usuario> usuarios = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Usuario u = new Usuario(null, 
                faker.internet().username(), 
                faker.name().fullName(), 
                faker.number().numberBetween(18, 70), 
                "https://picsum.photos/200/200?random=" + i);
            usuarios.add(usuarioDAO.save(u));
        }

        // Criando 30 posts distribuídos entre os usuários
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            Usuario user = usuarios.get(random.nextInt(5));
            
            // Gerando tags aleatórias, garantindo que algumas sejam "rpg" para o requisito 3
            List<String> tagNames = new ArrayList<>(List.of(faker.lorem().word(), faker.lorem().word()));
            if (i % 3 == 0) tagNames.add("rpg");
            
            // Gerando mensagem aleatória, garantindo que algumas contenham "orc" para o requisito 4
            String mensagem = faker.lorem().sentence();
            if (i % 5 == 0) mensagem += " O orc está chegando!";

            LocalDateTime dataAleatoria = faker.date()
                .past(30, TimeUnit.DAYS)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

            Post post = new Post(null, 
                dataAleatoria, 
                mensagem, 
                user.id(), 
                Tag.of(tagNames.toArray(new String[0])));
            
            postRepository.save(post);
        }
        log.info("Inserção de posts concluída.");
    }

    /**
     * Parte 2: Consultas Customizadas
     * Executa e imprime as consultas conforme requisitos 2, 3 e 4.
     */
    public void executarConsultasCustomizadas() {
        // Buscamos um usuário aleatório da base para testar o requisito 2
        var usuarioParaTeste = usuarioDAO.findAll().iterator().next();
        
        System.out.println("\n--- REQUISITO 2: Últimos 5 posts do usuário " + usuarioParaTeste.username() + " ---");
        postRepository.findTop5ByUsuarioIdOrderByDataPostagemDesc(usuarioParaTeste.id())
            .forEach(p -> System.out.println("ID: " + p.id() + " | Data: " + p.dataPostagem() + " | Mensagem: " + p.mensagem()));

        System.out.println("\n--- REQUISITO 3: Últimos 10 posts com tag 'rpg' ---");
        postRepository.findLast10ByTagName("rpg")
            .forEach(p -> System.out.println("ID: " + p.id() + " | Tags: " + p.tags() + " | Mensagem: " + p.mensagem()));

        System.out.println("\n--- REQUISITO 4: Posts que contenham 'orc' na mensagem ---");
        postRepository.findByMensagemContaining("orc")
            .forEach(p -> System.out.println("ID: " + p.id() + " | Mensagem: " + p.mensagem()));
    }

    /**
     * Parte 3: Otimização com Streams
     * Requisito 5: Utiliza Stream<Post> com try-with-resources para garantir o fechamento.
     */
    @Transactional(readOnly = true)
    public void imprimirUltimos15PostsStream() {
        System.out.println("\n--- REQUISITO 5: Últimos 15 posts via Stream ---");
        try (Stream<Post> postStream = postRepository.findLast15AsStream()) {
            postStream.forEach(p -> 
                System.out.println("Stream ID: " + p.id() + " | Mensagem: " + p.mensagem())
            );
        } catch (Exception e) {
            log.error("Erro ao processar stream de posts", e);
        }
    }

    /*
     * Parte 4: Teoria (Requisito 6)
     *
     * No Spring Data JDBC, para resolver o problema de consultas excessivas (N+1) em relacionamentos 1-1 ou 1-N:
     *
     * 1. Referência por ID (AggregateReference): É a forma recomendada pelo Spring Data JDBC para manter os 
     *    Agregados pequenos. Em vez de ter o objeto Nacionalidade dentro da Pessoa, temos um AggregateReference<Nacionalidade, Long>. 
     *    Isso evita o carregamento automático. Para exibir a Nacionalidade sem N+1, deve-se realizar uma consulta 
     *    customizada com JOIN no repositório ou usar uma projeção DTO que traga os dados relacionados.
     *
     * 2. Entidade Aninhada (Nested Entity) no mesmo Agregado: Se Nacionalidade e Pessoa fizerem parte do mesmo Agregado, 
     *    o framework permite injetar o objeto diretamente. O Spring Data JDBC carregará todos os dados em uma única 
     *    operação atômica (usando joins internos ou poucas queries planejadas) ao carregar o Aggregate Root. 
     *    Isso elimina o N+1 pois o framework já gerencia o ciclo de vida e o carregamento do conjunto de dados como um todo.
     *    A "Back Reference" é o termo usado quando o Agregado contém coleções, e o Spring Data JDBC mapeia o ID de volta
     *    (ex: a coluna post_id na tabela post_tag é uma back reference para o ID do Post).
     */
}
