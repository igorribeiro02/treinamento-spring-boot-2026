package br.uff.sti.ap4;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
@AllArgsConstructor
public class PostRestController {

    private final PostService postService;

    @GetMapping("{id}")
    public Post get(@PathVariable long id) {
        return this.postService.findObjById(id);
    }

    @GetMapping
    public List<Post> list(@PageableDefault(size = 10) Pageable pageable) {
        return this.postService.findAll(pageable).getContent();
    }
}
