package edu.badpals.bobesponjau4.controller;

import edu.badpals.bobesponjau4.model.Personaje;
import edu.badpals.bobesponjau4.model.Workplace;
import edu.badpals.bobesponjau4.service.PersonajeService;
import edu.badpals.bobesponjau4.service.WorkplaceService;
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

    @Autowired
    private WorkplaceService workplaceService;

    @GetMapping("/list")
    public String listPersonajes(Model model) {
        List<Personaje> personajes = personajeService.getAllPersonajes();
        model.addAttribute("personajes", personajes);
        return "personajes";
    }

    @GetMapping("/personajeform")
    public String showPersonajeForm(Model model) {
        model.addAttribute("personaje", new Personaje()); // Objeto vacío para el formulario
        List<Workplace> workplaces = workplaceService.getAllWorkplaces(); // Obtiene todos los lugares de trabajo
        model.addAttribute("workplaces", workplaces); // Agrega la lista al modelo
        return "personajeform"; // Devuelve la plantilla
    }

    @GetMapping("/")
    public String getPersonajeById(@RequestParam(name = "id", required = false) Integer id, Model model) {
        if (id == null) {
            return "redirect:/personajes/list"; // Si el ID no es válido, redirige al listado
        }

        Optional<Personaje> personajeOpt = personajeService.getPersonajeById(id);
        if (personajeOpt.isPresent()) {
            model.addAttribute("personaje", personajeOpt.get());
            List<Workplace> workplaces = workplaceService.getAllWorkplaces();
            model.addAttribute("workplaces", workplaces);
            return "editPersonaje"; // Carga la vista correcta
        }

        return "redirect:/personajes/list"; // Si el personaje no existe, vuelve al listado
    }


    @PostMapping("/guardar")
    public String savePersonaje(@ModelAttribute Personaje personaje) {
        personajeService.savePersonaje(personaje);
        return "redirect:/personajes/list";
    }

    @PostMapping("/editar")
    public String updatePersonaje(@ModelAttribute Personaje personaje) {
        if (personaje.getId() == null) {
            return "redirect:/personajes/list"; // Si el ID es nulo, vuelve al listado
        }

        Optional<Personaje> personajeOpt = personajeService.getPersonajeById(personaje.getId());
        if (personajeOpt.isPresent()) {
            Personaje personajeExistente = personajeOpt.get();
            personajeExistente.setName(personaje.getName());
            personajeExistente.setSpecies(personaje.getSpecies());
            personajeExistente.setAge(personaje.getAge());
            personajeExistente.setDescription(personaje.getDescription());
            personajeExistente.setWorkplace(personaje.getWorkplace());

            personajeService.savePersonaje(personajeExistente); // Guarda los cambios
            return "redirect:/personajes/list"; // Redirige al listado después de actualizar
        }

        return "redirect:/personajes/list"; // Si el personaje no existe, vuelve al listado
    }


    @GetMapping("/eliminar/{id}")
    public String deletePersonaje(@PathVariable Integer id) {
        personajeService.deletePersonaje(id);
        return "redirect:/personajes/list";
    }
}
