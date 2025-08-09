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
public class EtiquetaController {

    @Autowired
    private OpcionesEtiquetaService opcionesEtiquetaService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private EtiquetaService etiquetaService;

    @Autowired
    private OrdenEtiquetaService ordenEtiquetaService;

    @GetMapping("/crear-odt-etiqueta")
    public String verCrearOdtEtiqueta(Model model) {
        List<TipoPapelEtiqueta> listaTipoPapelEtiqueta = opcionesEtiquetaService.buscarTodosTipoPapelEtiqueta();
        List<TipoLaminadoEtiqueta> listaTipoLaminadoEtiqueta = opcionesEtiquetaService.buscarTodosTipoLaminadoEtiqueta();
        List<TamanioPerforacion> listaTamanioPerforacion = opcionesEtiquetaService.buscarTodosTamanioPerforacion();
        List<TipoFazEtiqueta> listaTipoFazEtiqueta = opcionesEtiquetaService.buscarTodosTipoFazEtiqueta();
        List<CantidadEtiqueta> listaCantidadEtiqueta = opcionesEtiquetaService.buscarTodosCantidadEtiqueta();
        List<MedidaEtiqueta> listaMedidaEtiqueta = opcionesEtiquetaService.buscarTodosMedidaEtiqueta();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("etiqueta", new Etiqueta());
        model.addAttribute("listaTipoPapelEtiqueta", listaTipoPapelEtiqueta);
        model.addAttribute("listaTipoLaminadoEtiqueta", listaTipoLaminadoEtiqueta);
        model.addAttribute("listaTamanioPerforacion", listaTamanioPerforacion);
        model.addAttribute("listaTipoFazEtiqueta", listaTipoFazEtiqueta);
        model.addAttribute("listaCantidadEtiqueta", listaCantidadEtiqueta);
        model.addAttribute("listaMedidaEtiqueta", listaMedidaEtiqueta);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-etiqueta";
    }

    @GetMapping("/mostrar-odt-etiqueta/{ordenEtiquetaId}")
    public String verOrdenEtiqueta(@PathVariable("ordenEtiquetaId") Long ordenEtiquetaId, Model model) {
        OrdenEtiqueta ordenEtiqueta = ordenEtiquetaService.buscarPorId(ordenEtiquetaId);

        model.addAttribute("ordenEtiqueta", ordenEtiqueta);

        return "mostrar-odt-etiqueta";
    }

    @PostMapping("/api/creacion-etiqueta")
    public String creacionEtiqueta(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Etiqueta etiqueta = etiquetaService.crear(request);
        OrdenEtiqueta ordenEtiqueta = ordenEtiquetaService.crear(ordenTrabajo, etiqueta);

        return "redirect:/mostrar-odt-etiqueta/" + ordenEtiqueta.getId();
    }
}
