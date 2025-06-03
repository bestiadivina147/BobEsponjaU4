package edu.badpals.bobesponjau4.controller;

import edu.badpals.bobesponjau4.model.Workplace;
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

    @GetMapping
    public String listWorkplaces(Model model) {
        List<Workplace> workplaces = workplaceService.getAllWorkplaces();
        model.addAttribute("workplaces", workplaces);
        return "workplaces";
    }

    @GetMapping("/{id}")
    public String getWorkplaceById(@PathVariable Integer id, Model model) {
        Optional<Workplace> workplace = workplaceService.getWorkplaceById(id);
        workplace.ifPresent(w -> model.addAttribute("workplace", w));
        return "workplace-detalle";
    }

    @PostMapping("/guardar")
    public String saveWorkplace(@ModelAttribute Workplace workplace) {
        workplaceService.saveWorkplace(workplace);
        return "redirect:/workplaces";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteWorkplace(@PathVariable Integer id) {
        workplaceService.deleteWorkplace(id);
        return "redirect:/workplaces";
    }
}

