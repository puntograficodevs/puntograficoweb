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
public class SobreController {

    @Autowired
    private SobreService sobreService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OpcionesSobreService opcionesSobreService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private OrdenSobreService ordenSobreService;

    @GetMapping("/crear-odt-sobre")
    public String verCrearOdtSobre(Model model) {
        List<MedidaSobre> listaMedidaSobre = opcionesSobreService.buscarTodosMedidaSobre();
        List<TipoColorSobre> listaTipoColorSobre = opcionesSobreService.buscarTodosTipoColorSobre();
        List<CantidadSobre> listaCantidadSobre = opcionesSobreService.buscarTodosCantidadSobre();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("sobre", new Sobre());
        model.addAttribute("listaMedidaSobre", listaMedidaSobre);
        model.addAttribute("listaTipoColorSobre", listaTipoColorSobre);
        model.addAttribute("listaCantidadSobre", listaCantidadSobre);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-sobre";
    }

    @GetMapping("/mostrar-odt-sobre/{ordenSobreId}")
    public String verOrdenSobre(@PathVariable("ordenSobreId") Long ordenSobreId, Model model) {
        OrdenSobre ordenSobre = ordenSobreService.buscarPorId(ordenSobreId);

        model.addAttribute("ordenSobre", ordenSobre);

        return "mostrar-odt-sobre";
    }

    @PostMapping("/api/creacion-sobre")
    public String creacionSobre(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Sobre sobre = sobreService.crear(request);
        OrdenSobre ordenSobre = ordenSobreService.crear(ordenTrabajo, sobre);
        return "redirect:/mostrar-odt-sobre/" + ordenSobre.getId();
    }
}
