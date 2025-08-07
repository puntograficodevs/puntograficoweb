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
public class CierraBolsasController {

    @Autowired
    private OpcionesCierraBolsasService opcionesCierraBolsasService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenCierraBolsasService ordenCierraBolsasService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private CierraBolsasService cierraBolsasService;

    @GetMapping("/crear-odt-cierra-bolsas")
    public String verCrearOdtCierraBolsas(Model model) {
        List<TipoTroqueladoCierraBolsas> listaTipoTroqueladoCierraBolsas = opcionesCierraBolsasService.buscarTodosTipoTroqueladoCierraBolsas();
        List<MedidaCierraBolsas> listaMedidaCierraBolsas = opcionesCierraBolsasService.buscarTodosMedidaCierraBolsas();
        List<CantidadCierraBolsas> listaCantidadCierraBolsas = opcionesCierraBolsasService.buscarTodosCantidadCierraBolsas();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("cierraBolsas", new CierraBolsas());
        model.addAttribute("listaTipoTroqueladoCierraBolsas", listaTipoTroqueladoCierraBolsas);
        model.addAttribute("listaMedidaCierraBolsas", listaMedidaCierraBolsas);
        model.addAttribute("listaCantidadCierraBolsas", listaCantidadCierraBolsas);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-cierra-bolsas";
    }

    @GetMapping("/mostrar-odt-cierra-bolsas/{ordenCierraBolsasId}")
    public String verOrdenCierraBolsas(@PathVariable("ordenCierraBolsasId") Long ordenCierraBolsasId, Model model) {
        OrdenCierraBolsas ordenCierraBolsas = ordenCierraBolsasService.buscarPorId(ordenCierraBolsasId);

        model.addAttribute("ordenCierraBolsas", ordenCierraBolsas);

        return "mostrar-odt-cierra-bolsas";
    }

    @PostMapping("/api/creacion-cierra-bolsas")
    public String creacionCierraBolsas(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        CierraBolsas cierraBolsas = cierraBolsasService.crear(request);
        OrdenCierraBolsas ordenCierraBolsas = ordenCierraBolsasService.crear(ordenTrabajo, cierraBolsas);
        return "redirect:/mostrar-odt-cierra-bolsas/" + ordenCierraBolsas.getId();
    }
}
