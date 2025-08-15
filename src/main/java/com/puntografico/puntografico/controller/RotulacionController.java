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
public class RotulacionController {

    @Autowired
    private OpcionesRotulacionService opcionesRotulacionService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private RotulacionService rotulacionService;

    @Autowired
    private OrdenRotulacionService ordenRotulacionService;

    @GetMapping("/crear-odt-rotulacion")
    public String verCrearOdtRotulacion(Model model) {
        List<TipoRotulacion> listaTipoRotulacion = opcionesRotulacionService.buscarTodosTipoRotulacion();
        List<TipoCorteRotulacion> listaTipoCorteRotulacion = opcionesRotulacionService.buscarTodosTipoCorteRotulacion();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("rotulacion", new Rotulacion());
        model.addAttribute("listaTipoRotulacion", listaTipoRotulacion);
        model.addAttribute("listaTipoCorteRotulacion", listaTipoCorteRotulacion);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-rotulacion";
    }

    @GetMapping("/mostrar-odt-rotulacion/{ordenRotulacionId}")
    public String verOrdenRotulacion(@PathVariable("ordenRotulacionId") Long ordenRotulacionId, Model model) {
        OrdenRotulacion ordenRotulacion = ordenRotulacionService.buscarPorId(ordenRotulacionId);

        model.addAttribute("ordenRotulacion", ordenRotulacion);

        return "mostrar-odt-rotulacion";
    }

    @PostMapping("/api/creacion-rotulacion")
    public String creacionProducto(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Rotulacion rotulacion = rotulacionService.crear(request);
        OrdenRotulacion ordenRotulacion = ordenRotulacionService.crear(ordenTrabajo, rotulacion);

        return "redirect:/mostrar-odt-rotulacion/" + ordenRotulacion.getId();
    }
}
