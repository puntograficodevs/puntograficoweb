package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.Empleado;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.service.EmpleadoService;
import com.puntografico.puntografico.service.OrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ListadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @GetMapping("/listado")
    public String listado(HttpSession session, Model model) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        List<OrdenTrabajo> ordenesSinHacer = ordenTrabajoService.buscarEstadoSinHacer();
        List<OrdenTrabajo> ordenesCorregir = ordenTrabajoService.buscarEstadoCorregir();
        List<OrdenTrabajo> ordenesEnProceso = ordenTrabajoService.buscarEstadoEnProceso();
        List<OrdenTrabajo> ordenesListaParaRetirar = ordenTrabajoService.buscarEstadoListaParaRetirar();

        model.addAttribute("empleado", empleado);
        model.addAttribute("ordenesSinHacer", ordenesSinHacer);
        model.addAttribute("ordenesCorregir", ordenesCorregir);
        model.addAttribute("ordenesEnProceso", ordenesEnProceso);
        model.addAttribute("ordenesListaParaRetirar", ordenesListaParaRetirar);

        return "listado";
    }
}
