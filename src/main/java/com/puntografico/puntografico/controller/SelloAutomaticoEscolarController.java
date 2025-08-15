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
public class SelloAutomaticoEscolarController {

    @Autowired
    private OpcionesSelloAutomaticoEscolarService opcionesSelloAutomaticoEscolarService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private SelloAutomaticoEscolarService selloAutomaticoEscolarService;

    @Autowired
    private OrdenSelloAutomaticoEscolarService ordenSelloAutomaticoEscolarService;

    @GetMapping("/crear-odt-sello-automatico-escolar")
    public String verCrearOdtSelloAutomaticoEscolar(Model model) {
        List<ModeloSelloAutomaticoEscolar> listaModeloSelloAutomaticoEscolar = opcionesSelloAutomaticoEscolarService.buscarTodosModeloSelloAutomaticoEscolar();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("selloAutomaticoEscolar", new SelloAutomaticoEscolar());
        model.addAttribute("listaModeloSelloAutomaticoEscolar", listaModeloSelloAutomaticoEscolar);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-sello-automatico-escolar";
    }

    @GetMapping("/mostrar-odt-sello-automatico-escolar/{ordenSelloAutomaticoEscolarId}")
    public String verOrdenSelloAutomaticoEscolar(@PathVariable("ordenSelloAutomaticoEscolarId") Long ordenSelloAutomaticoEscolarId, Model model) {
        OrdenSelloAutomaticoEscolar ordenSelloAutomaticoEscolar = ordenSelloAutomaticoEscolarService.buscarPorId(ordenSelloAutomaticoEscolarId);

        model.addAttribute("ordenSelloAutomaticoEscolar", ordenSelloAutomaticoEscolar);

        return "mostrar-odt-sello-automatico-escolar";
    }

    @PostMapping("/api/creacion-sello-automatico-escolar")
    public String creacionSelloAutomaticoEscolar(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        SelloAutomaticoEscolar selloAutomaticoEscolar = selloAutomaticoEscolarService.crear(request);
        OrdenSelloAutomaticoEscolar ordenSelloAutomaticoEscolar = ordenSelloAutomaticoEscolarService.crear(ordenTrabajo, selloAutomaticoEscolar);

        return "redirect:/mostrar-odt-sello-automatico-escolar/" + ordenSelloAutomaticoEscolar.getId();
    }
}
