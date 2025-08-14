package br.com.fiap.ovnis.ovnis;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ovnis")
public class OvnisController {

    private final OvnisRepository repository;

    public OvnisController(OvnisRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String index(Model model) {
        var ovnis = repository.findAll();
        model.addAttribute("ovnis", ovnis);
        return "index";
    }

    @GetMapping("/form")
    public String form(){
        return "form";
    }

    @PostMapping("/form")
    public String create(Ovnis ovnis, RedirectAttributes redirect){
        repository.save(ovnis);
        redirect.addFlashAttribute("message", "Ovnis Cadastrado com sucesso!");
        return "redirect:/ovnis";
    }
}
