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
@RequestMapping("/workplaces") // Ruta base
public class WorkplaceController {

    @Autowired
    private WorkplaceService workplaceService;

    @Autowired
    private PersonajeService personajeService;

    @GetMapping("/list")
    public String listWorkplaces(Model model) {
        List<Workplace> workplaces = workplaceService.getAllWorkplaces();
        model.addAttribute("workplaces", workplaces);
        return "workplaces";
    }
    @GetMapping("/workplaceform")
    public String showWorkplaceForm(Model model) {
        model.addAttribute("workplace", new Workplace()); // Objeto vacío para el formulario
        return "workplaceform"; // Devuelve la plantilla
    }

    @GetMapping("/")
    public String getWorkplaceById(@RequestParam(name = "id", required = false) Integer id, Model model) {
        if (id == null) {
            return "redirect:/workplaces/list";
        }

        Optional<Workplace> workplaceOptional = workplaceService.getWorkplaceById(id);
        if (workplaceOptional.isPresent()) {
            model.addAttribute("workplace", workplaceOptional.get());
            return "editWorkplace";
        }

        return "redirect:/workplaces/list";
    }

    @PostMapping("/editar")
    public String updatePersonaje(@ModelAttribute Workplace workplace) {
        if (workplace.getId() == null) {
            return "redirect:/workplaces/list";
        }

        Optional<Workplace> workplaceOptional = workplaceService.getWorkplaceById(workplace.getId());
        if (workplaceOptional.isPresent()) {
            Workplace workplaceExistente = workplaceOptional.get();
            workplaceExistente.setName(workplace.getName());
            workplaceExistente.setLocation(workplace.getLocation());
            workplaceExistente.setDescription(workplace.getDescription());

            workplaceService.saveWorkplace(workplaceExistente);
            return "redirect:/workplaces/list";
        }

        return "redirect:/workplaces/list";
    }

    @PostMapping("/guardar")
    public String saveWorkplace(@ModelAttribute Workplace workplace) {
        workplaceService.saveWorkplace(workplace);
        return "redirect:/workplaces/list";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteWorkplace(@PathVariable Integer id) {
        Optional<Workplace> workplaceOpt = workplaceService.getWorkplaceById(id);

        if (workplaceOpt.isPresent()) {
            Workplace workplace = workplaceOpt.get();

            // Eliminar la relación de los personajes con este Workplace
            List<Personaje> personajes = personajeService.getPersonajesByWorkplace(workplace);
            for (Personaje personaje : personajes) {
                personaje.setWorkplace(null);
                personajeService.savePersonaje(personaje); // Guardar el cambio
            }

            // Ahora eliminar el Workplace
            workplaceService.deleteWorkplace(id);
        }

        return "redirect:/workplaces/list";
    }

}

