package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.service.AgendaService;
import com.puntografico.puntografico.service.MedioPagoService;
import com.puntografico.puntografico.service.OpcionesAgendaService;
import com.puntografico.puntografico.service.OrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/api/creacion-agenda")
    public String creacionAgenda(HttpServletRequest request) {
        ordenTrabajoService.crear(request);
        agendaService.crear(request);
        return "redirect:/home"; // O donde quieras redirigir
    }
}
