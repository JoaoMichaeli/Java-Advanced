package br.com.fiap.aicharactercreator.character;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;
    private final MessageSource messageSource;

    @GetMapping
    public String index(Model model) {
        var characters = characterService.getAllCharacters();
        model.addAttribute("characters", characters);
        return "characters";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("character", new Character());
        return "new";
    }

    @PostMapping("/new")
    public String create(@Valid Character character, BindingResult result, Model model, RedirectAttributes redirect){

        if(result.hasErrors()) return "new";

        var message = messageSource.getMessage("character.create.success", null, LocaleContextHolder.getLocale());
        characterService.save(character);
        redirect.addFlashAttribute("message", message);
        return "redirect:/characters";

    }

}