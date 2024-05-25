package org.example.clase7labmarvel.controller;

import org.example.clase7labmarvel.entity.Character;
import org.example.clase7labmarvel.repository.CharacterRepository;
import org.example.clase7labmarvel.repository.RoleRepository;
import org.example.clase7labmarvel.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/ws/personaje")
public class WSController {

    // Repositorios:

    final CharacterRepository characterRepository;
    final RoleRepository roleRepository;
    final UserRepository userRepository;

    public WSController(CharacterRepository characterRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.characterRepository = characterRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    //Métodos

    //OBTENER
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<HashMap<String, Object>> buscarPersonaje(@PathVariable("id") String idStr) {

        HashMap<String, Object> respuesta = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
            Optional<Character> byId = characterRepository.findById(id);
            if (byId.isPresent()) {
                respuesta.put("", byId.get());
                return ResponseEntity.ok(respuesta);
            } else {
                respuesta.put("error", "ID Personaje NO encontrado.");
                respuesta.put("date", LocalDateTime.now());
                return ResponseEntity.badRequest().body(respuesta);
            }
        } catch (NumberFormatException e) {
            respuesta.put("error", "ID Personaje debe ser un número entero.");
            respuesta.put("date", LocalDateTime.now());
            return ResponseEntity.badRequest().body(respuesta);
        }
    }


}
