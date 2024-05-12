package org.example.clase7labmarvel.controller;

import jakarta.validation.Valid;
import org.example.clase7labmarvel.entity.Character;
import org.example.clase7labmarvel.repository.CharacterRepository;
import org.example.clase7labmarvel.repository.RoleRepository;
import org.example.clase7labmarvel.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/personaje")
public class PersonajeController {

    // Repositorios:
    final CharacterRepository characterRepository;
    final RoleRepository roleRepository;
    final UserRepository userRepository;

    public PersonajeController(CharacterRepository characterRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.characterRepository = characterRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    // Métodos:

    @GetMapping(value = {"", "/list"})
    public String listPersonajes(Model model, @RequestParam(name = "search", required = false) String search) {

        if(search == null){
            model.addAttribute("listaPersonajes", characterRepository.findAll());
        }else{
            model.addAttribute("listaPersonajes", characterRepository.findByNameIgnoreCaseContaining(search));
            model.addAttribute("search",search);
        }
        return "list";
    }

    @GetMapping("/new")
    public String nuevoPersonajeFrm(@ModelAttribute("personaje") Character personaje) {
        return "form";
    }

    @GetMapping("/edit")
    public String editarPersonajeFrm(Model model, @RequestParam("id") int id,
                                      @ModelAttribute("personaje") Character personaje) {

        Optional<Character> optCharacter = characterRepository.findById(id);

        if (optCharacter.isPresent()) {
            personaje = optCharacter.get();
            model.addAttribute("personaje", personaje);
            return "form";
        } else {
            return "redirect:/personaje/list";
        }
    }

    @PostMapping("/save")
    public String guardarPersonaje(@ModelAttribute("personaje") @Valid Character personaje, BindingResult bindingResult,
                                  RedirectAttributes attr) {

        if (bindingResult.hasErrors()) {
            return "form";
        } else {
            if (personaje.getId() == 0) {
                attr.addFlashAttribute("msg", "Personaje creado exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Personaje actualizado exitosamente");
            }
            characterRepository.save(personaje);
            return "redirect:/personaje/list";
        }
    }


}
