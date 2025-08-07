package br.com.fiap.SuperPoderes.powers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/power")
public class PowerController {

    private final PowerService powerService;

    public PowerController(PowerService powerService) {
        this.powerService = powerService;
    }

    @GetMapping
    public String listarPorInutilidade(Model model) {
        List<Power> powers = powerService.getForOrderOfUselessness();
        model.addAttribute("power", powers);
        return "index";
    }
}
