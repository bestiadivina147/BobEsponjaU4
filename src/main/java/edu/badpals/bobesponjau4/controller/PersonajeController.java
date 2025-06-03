package edu.badpals.bobesponjau4.controller;

import edu.badpals.bobesponjau4.model.Personaje;
import edu.badpals.bobesponjau4.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/personajes") // Ruta base
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping("/list")
    public String listPersonajes(Model model) {
        List<Personaje> personajes = personajeService.getAllPersonajes();
        model.addAttribute("personajes", personajes);
        return "personajes";
    }

    @GetMapping("/{id}")
    public String getPersonajeById(@PathVariable Integer id, Model model) {
        Optional<Personaje> personaje = personajeService.getPersonajeById(id);
        personaje.ifPresent(p -> model.addAttribute("personaje", p));
        return "personaje-detalle";
    }

    @PostMapping("/guardar")
    public String savePersonaje(@ModelAttribute Personaje personaje) {
        personajeService.savePersonaje(personaje);
        return "redirect:/personajes";
    }

    @GetMapping("/eliminar/{id}")
    public String deletePersonaje(@PathVariable Integer id) {
        personajeService.deletePersonaje(id);
        return "redirect:/personajes";
    }
}
