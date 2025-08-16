package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ViniloDeCorteController {

    @Autowired
    private OpcionesViniloDeCorteService opcionesViniloDeCorteService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private ViniloDeCorteService viniloDeCorteService;

    @Autowired
    private OrdenViniloDeCorteService ordenViniloDeCorteService;

    @GetMapping("/crear-odt-vinilo-de-corte")
    public String verCrearOdtViniloDeCorte(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<TraeMaterialVinilo> listaTraeMaterialVinilo = opcionesViniloDeCorteService.buscarTodosTraeMaterialVinilo();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("viniloDeCorte", new ViniloDeCorte());
        model.addAttribute("listaTraeMaterialVinilo", listaTraeMaterialVinilo);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-vinilo-de-corte";
    }

    @GetMapping("/mostrar-odt-vinilo-de-corte/{ordenViniloDeCorteId}")
    public String verOrdenViniloDeCorte(@PathVariable("ordenViniloDeCorteId") Long ordenViniloDeCorteId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenViniloDeCorte ordenViniloDeCorte = ordenViniloDeCorteService.buscarPorId(ordenViniloDeCorteId);

        model.addAttribute("ordenViniloDeCorte", ordenViniloDeCorte);

        return "mostrar-odt-vinilo-de-corte";
    }

    @PostMapping("/api/creacion-vinilo-de-corte")
    public String creacionProducto(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        ViniloDeCorte viniloDeCorte = viniloDeCorteService.crear(request);
        OrdenViniloDeCorte ordenViniloDeCorte = ordenViniloDeCorteService.crear(ordenTrabajo, viniloDeCorte);

        return "redirect:/mostrar-odt-vinilo-de-corte/" + ordenViniloDeCorte.getId();
    }
}
