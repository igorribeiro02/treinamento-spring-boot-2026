package br.uff.sti.ap4;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
@RequestMapping("post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final UsuarioDAO usuarioDAO;

    @GetMapping
    public ModelAndView list(@PageableDefault(size = 10) Pageable pageable) {
        val mv = new ModelAndView("post/list");
        mv.addObject("posts", postService.findAll(pageable));
        return mv;
    }

    @GetMapping("{id:[0-9]+}")
    public ModelAndView get(@PathVariable Long id) {
        val mv = new ModelAndView("post/get");
        mv.addObject("post", postService.findObyComUsuarioById(id));
        return mv;
    }

    @GetMapping("new")
    public String newPost(Model model) {
        model.addAttribute("post", new Post(null, LocalDateTime.now(), "", null, new ArrayList<>()));
        model.addAttribute("usuarios", usuarioDAO.findAll());
        return "post/edit";
    }

    @GetMapping("{id}/edit")
    public String editPost(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.findObjById(id));
        model.addAttribute("usuarios", usuarioDAO.findAll());
        return "post/edit";
    }

    @PostMapping("save")
    public String save(@ModelAttribute Post post) {
        Post toSave = post;
        if (post.dataPostagem() == null) {
            toSave = toSave.withDataPostagem(LocalDateTime.now());
        }
        if (post.tags() == null) {
            toSave = toSave.withTags(new ArrayList<>());
        }
        postService.save(toSave);
        return "redirect:/post";
    }
}
