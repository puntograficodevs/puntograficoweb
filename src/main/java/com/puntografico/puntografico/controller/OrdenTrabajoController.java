package com.puntografico.puntografico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdenTrabajoController {

    @GetMapping("/crear-orden")
    public String verCrearOrden(Model model) {
        return "crear-orden";
    }


}
