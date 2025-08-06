package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.service.AnotadorService;
import com.puntografico.puntografico.service.MedioPagoService;
import com.puntografico.puntografico.service.OrdenAnotadorService;
import com.puntografico.puntografico.service.OrdenTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AnotadorController {

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenAnotadorService ordenAnotadorService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private AnotadorService anotadorService;


    @GetMapping("/crear-odt-anotador")
    public String verCrearOdtAnotador(Model model) {
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("anotador", new Anotador());
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-anotador";
    }

    @GetMapping("/mostrar-odt-anotador/{ordenAnotadorId}")
    public String verOrdenAnotador(@PathVariable("ordenAnotadorId") Long ordenAnotadorId, Model model) {
        OrdenAnotador ordenAnotador = ordenAnotadorService.buscarPorId(ordenAnotadorId);

        model.addAttribute("ordenAnotador", ordenAnotador);

        return "mostrar-odt-anotador";
    }

    @PostMapping("/api/creacion-anotador")
    public String creacionAnotador(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Anotador anotador = anotadorService.crear(request);
        OrdenAnotador ordenAnotador = ordenAnotadorService.crear(ordenTrabajo, anotador);
        return "redirect:/mostrar-odt-anotador/" + ordenAnotador.getId();
    }
}
