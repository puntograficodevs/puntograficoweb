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
import java.util.List;

@Controller
public class SublimacionController {
    @Autowired
    private SublimacionService sublimacionService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OpcionesSublimacionService opcionesSublimacionService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private OrdenSublimacionService ordenSublimacionService;

    @GetMapping("/crear-odt-sublimacion")
    public String verCrearOdtSublimacion(Model model) {
        List<MaterialSublimacion> listaMaterialSublimacion = opcionesSublimacionService.buscarTodosMaterialSublimacion();
        List<CantidadSublimacion> listaCantidadSublimacion = opcionesSublimacionService.buscarTodosCantidadSublimacion();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("sublimacion", new Sublimacion());
        model.addAttribute("listaMaterialSublimacion", listaMaterialSublimacion);
        model.addAttribute("listaCantidadSublimacion", listaCantidadSublimacion);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-sublimacion";
    }

    @GetMapping("/mostrar-odt-sublimacion/{ordenSublimacionId}")
    public String verOrdenSublimacion(@PathVariable("ordenSublimacionId") Long ordenSublimacionId, Model model) {
        OrdenSublimacion ordenSublimacion = ordenSublimacionService.buscarPorId(ordenSublimacionId);

        model.addAttribute("ordenSublimacion", ordenSublimacion);

        return "mostrar-odt-sublimacion";
    }

    @PostMapping("/api/creacion-sublimacion")
    public String creacionSublimacion(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Sublimacion sublimacion = sublimacionService.crear(request);
        OrdenSublimacion ordenSublimacion = ordenSublimacionService.crear(ordenTrabajo, sublimacion);
        return "redirect:/mostrar-odt-sublimacion/" + ordenSublimacion.getId();
    }
}
