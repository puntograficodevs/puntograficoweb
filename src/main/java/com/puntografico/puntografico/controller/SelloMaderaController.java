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
public class SelloMaderaController {

    @Autowired
    private OpcionesSelloMaderaService opcionesSelloMaderaService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private SelloMaderaService selloMaderaService;

    @Autowired
    private OrdenSelloMaderaService ordenSelloMaderaService;

    @GetMapping("/crear-odt-sello-madera")
    public String verCrearOdtSelloMadera(Model model) {
        List<TamanioSelloMadera> listaTamanioSelloMadera = opcionesSelloMaderaService.buscarTodosTamanioSelloMadera();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("selloMadera", new SelloMadera());
        model.addAttribute("listaTamanioSelloMadera", listaTamanioSelloMadera);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-sello-madera";
    }

    @GetMapping("/mostrar-odt-sello-madera/{ordenSelloMaderaId}")
    public String verOrdenSelloMadera(@PathVariable("ordenSelloMaderaId") Long ordenSelloMaderaId, Model model) {
        OrdenSelloMadera ordenSelloMadera = ordenSelloMaderaService.buscarPorId(ordenSelloMaderaId);

        model.addAttribute("ordenSelloMadera", ordenSelloMadera);

        return "mostrar-odt-sello-madera";
    }

    @PostMapping("/api/creacion-sello-madera")
    public String creacionSelloMadera(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        SelloMadera selloMadera = selloMaderaService.crear(request);
        OrdenSelloMadera ordenSelloMadera = ordenSelloMaderaService.crear(ordenTrabajo, selloMadera);

        return "redirect:/mostrar-odt-sello-madera/" + ordenSelloMadera.getId();
    }
}
