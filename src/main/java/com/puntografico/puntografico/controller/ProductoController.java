package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.Empleado;
import com.puntografico.puntografico.domain.Producto;
import com.puntografico.puntografico.service.EmpleadoService;
import com.puntografico.puntografico.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/creacionProducto")
    public String home(@RequestParam String username, Model model) {
        if(username == null || username.isEmpty()) {
            return "redirect:/";
        }

        Empleado empleado = empleadoService.traerEmpleadoPorUsername(username);

        model.addAttribute("empleado", empleado);
        return "creacionProducto";
    }

    @PostMapping("/producto/crear")
    public String crearProducto(@RequestParam String nombre, RedirectAttributes redirectAttributes) {
        Producto producto = productoService.crear(nombre);

        redirectAttributes.addFlashAttribute("productoId", producto.getId());
        return "redirect:/creacionProducto";
    }
}
