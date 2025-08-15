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
public class SelloAutomaticoController {

    @Autowired
    private OpcionesSelloAutomaticoService opcionesSelloAutomaticoService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private SelloAutomaticoService selloAutomaticoService;

    @Autowired
    private OrdenSelloAutomaticoService ordenSelloAutomaticoService;

    @GetMapping("/crear-odt-sello-automatico")
    public String verCrearOdtSelloAutomatico(Model model) {
        List<ModeloSelloAutomatico> listaModeloSelloAutomatico = opcionesSelloAutomaticoService.buscarTodosModeloSelloAutomatico();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("selloAutomatico", new SelloAutomatico());
        model.addAttribute("listaModeloSelloAutomatico", listaModeloSelloAutomatico);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-sello-automatico";
    }

    @GetMapping("/mostrar-odt-sello-automatico/{ordenSelloAutomaticoId}")
    public String verOrdenSelloAutomatico(@PathVariable("ordenSelloAutomaticoId") Long ordenSelloAutomaticoId, Model model) {
        OrdenSelloAutomatico ordenSelloAutomatico = ordenSelloAutomaticoService.buscarPorId(ordenSelloAutomaticoId);

        model.addAttribute("ordenSelloAutomatico", ordenSelloAutomatico);

        return "mostrar-odt-sello-automatico";
    }

    @PostMapping("/api/creacion-sello-automatico")
    public String creacionSelloAutomatico(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        SelloAutomatico selloAutomatico = selloAutomaticoService.crear(request);
        OrdenSelloAutomatico ordenSelloAutomatico = ordenSelloAutomaticoService.crear(ordenTrabajo, selloAutomatico);

        return "redirect:/mostrar-odt-sello-automatico/" + ordenSelloAutomatico.getId();
    }
}
