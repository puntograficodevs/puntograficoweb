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
public class HojasMembreteadasController {

    @Autowired
    private OpcionesHojasMembreteadasService opcionesHojasMembreteadasService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private HojasMembreteadasService hojasMembreteadasService;

    @Autowired
    private OrdenHojasMembreteadasService ordenHojasMembreteadasService;

    @GetMapping("/crear-odt-hojas-membreteadas")
    public String verCreadOdtHojasMembreteadas(Model model) {
        List<MedidaHojasMembreteadas> listaMedidaHojasMembreteadas = opcionesHojasMembreteadasService.buscarTodosMedidaHojasMembreteadas();
        List<TipoColorHojasMembreteadas> listaTipoColorHojasMembreteadas = opcionesHojasMembreteadasService.buscarTodosTipoColorHojasMembreteadas();
        List<CantidadHojasMembreteadas> listaCantidadHojasMembreteadas = opcionesHojasMembreteadasService.buscarTodosCantidadHojasMembreteadas();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("hojasMembreteadas", new HojasMembreteadas());
        model.addAttribute("listaMedidaHojasMembreteadas", listaMedidaHojasMembreteadas);
        model.addAttribute("listaTipoColorHojasMembreteadas", listaTipoColorHojasMembreteadas);
        model.addAttribute("listaCantidadHojasMembreteadas", listaCantidadHojasMembreteadas);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-hojas-membreteadas";
    }

    @GetMapping("/mostrar-odt-hojas-membreteadas/{ordenHojasMembreteadasId}")
    public String verOrdenHojasMembreteadas(@PathVariable("ordenHojasMembreteadasId") Long ordenHojasMembreteadasId, Model model) {
        OrdenHojasMembreteadas ordenHojasMembreteadas = ordenHojasMembreteadasService.buscarPorId(ordenHojasMembreteadasId);

        model.addAttribute("ordenHojasMembreteadas", ordenHojasMembreteadas);

        return "mostrar-odt-hojas-membreteadas";
    }

    @PostMapping("/api/creacion-hojas-membreteadas")
    public String creacionProducto(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        HojasMembreteadas hojasMembreteadas = hojasMembreteadasService.crear(request);
        OrdenHojasMembreteadas ordenHojasMembreteadas = ordenHojasMembreteadasService.crear(ordenTrabajo, hojasMembreteadas);

        return "redirect:/mostrar-odt-hojas-membreteadas/" + ordenHojasMembreteadas.getId();
    }
}
