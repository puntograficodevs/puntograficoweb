package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.Empleado;
import com.puntografico.puntografico.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/creacionProducto")
    public String home(@RequestParam String username, Model model) {
        if(username == null || username.isEmpty()) {
            return "redirect:/";
        }

        Empleado empleado = empleadoService.traerEmpleadoPorUsername(username);

        model.addAttribute("empleado", empleado);
        return "creacionProducto";
    }
}
