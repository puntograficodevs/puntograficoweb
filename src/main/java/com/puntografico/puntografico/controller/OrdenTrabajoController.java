package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.service.OrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrdenTrabajoController {

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @GetMapping("/crear-orden")
    public String verCrearOrden(Model model) {
        model.addAttribute("ordenTrabajo", new OrdenTrabajo());
        return "crear-orden";
    }
}
