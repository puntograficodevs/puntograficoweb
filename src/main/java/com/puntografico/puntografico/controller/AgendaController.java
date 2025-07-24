package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OpcionesAgendaService opcionesAgendaService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private OrdenAgendaService ordenAgendaService;

    @GetMapping("/crear-odt-agenda")
    public String verCrearOdtAgenda(Model model) {
        List<TipoTapaAgenda> listaTipoTapaAgenda = opcionesAgendaService.buscarTodosTipoTapaAgenda();
        List<TipoColorAgenda> listaTipoColorAgenda = opcionesAgendaService.buscarTodosTipoColorAgenda();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("agenda", new Agenda());
        model.addAttribute("listaTipoTapaAgenda", listaTipoTapaAgenda);
        model.addAttribute("listaTipoColorAgenda", listaTipoColorAgenda);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-agenda";
    }

    @GetMapping("/mostrar-odt-agenda/{ordenAgendaId}")
    public String verOrdenAgenda(@PathVariable("ordenAgendaId") Long ordenAgendaId, Model model) {
        OrdenAgenda ordenAgenda = ordenAgendaService.buscarPorId(ordenAgendaId);

        model.addAttribute("ordenAgenda", ordenAgenda);

        return "mostrar-odt-agenda";
    }

    @PostMapping("/api/creacion-agenda")
    public String creacionAgenda(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Agenda agenda = agendaService.crear(request);
        OrdenAgenda ordenAgenda = ordenAgendaService.crear(ordenTrabajo, agenda);
        return "redirect:/mostrar-odt-agenda/" + ordenAgenda.getId();
    }


}
