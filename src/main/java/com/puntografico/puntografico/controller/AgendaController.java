package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.Agenda;
import com.puntografico.puntografico.domain.TipoColorAgenda;
import com.puntografico.puntografico.domain.TipoTapaAgenda;
import com.puntografico.puntografico.service.AgendaService;
import com.puntografico.puntografico.service.OpcionesAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private OpcionesAgendaService opcionesAgendaService;

    @GetMapping("/crear-odt-agenda")
    public String verCrearOdtAgenda(Model model) {
        List<TipoTapaAgenda> listaTipoTapaAgenda = opcionesAgendaService.buscarTodosTipoTapaAgenda();
        List<TipoColorAgenda> listaTipoColorAgenda = opcionesAgendaService.buscarTodosTipoColorAgenda();

        model.addAttribute("agenda", new Agenda());
        model.addAttribute("listaTipoTapaAgenda", listaTipoTapaAgenda);
        model.addAttribute("listaTipoColorAgenda", listaTipoColorAgenda);
        return "crear-odt-agenda";
    }

    @PostMapping("/api/creacion-agenda")
    public String creacionAgenda(@ModelAttribute Agenda agenda) {
        agendaService.guardar(agenda);
        return "redirect:/home"; // O donde quieras redirigir
    }
}
