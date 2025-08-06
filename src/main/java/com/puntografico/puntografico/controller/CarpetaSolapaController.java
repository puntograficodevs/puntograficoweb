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
public class CarpetaSolapaController {

    @Autowired
    private OpcionesCarpetaSolapaService opcionesCarpetaSolapaService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private CarpetaSolapaService carpetaSolapaService;

    @Autowired
    private OrdenCarpetaSolapaService ordenCarpetaSolapaService;

    @GetMapping("/crear-odt-carpeta-solapa")
    public String verCrearOdtCarpetaSolapa(Model model) {
        List<TipoFazCarpetaSolapa> listaTipoFazCarpetaSolapa = opcionesCarpetaSolapaService.buscarTodosTipoFazCarpetaSolapa();
        List<TipoLaminadoCarpetaSolapa> listaTipoLaminadoCarpetaSolapa = opcionesCarpetaSolapaService.buscarTodosTipoLaminadoCarpetaSolapa();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("carpetaSolapa", new CarpetaSolapa());
        model.addAttribute("listaTipoFazCarpetaSolapa", listaTipoFazCarpetaSolapa);
        model.addAttribute("listaTipoLaminadoCarpetaSolapa", listaTipoLaminadoCarpetaSolapa);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-carpeta-solapa";
    }

    @GetMapping("/mostrar-odt-carpeta-solapa/{ordenCarpetaSolapaId}")
    public String verOrdenCarpetaSolapa(@PathVariable("ordenCarpetaSolapaId") Long ordenCarpetaSolapaId, Model model) {
        OrdenCarpetaSolapa ordenCarpetaSolapa = ordenCarpetaSolapaService.buscarPorId(ordenCarpetaSolapaId);

        model.addAttribute("ordenCarpetaSolapa", ordenCarpetaSolapa);

        return "mostrar-odt-carpeta-solapa";
    }

    @PostMapping("/api/creacion-carpeta-solapa")
    public String creacionCarpetaSolapa(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        CarpetaSolapa carpetaSolapa = carpetaSolapaService.crear(request);
        OrdenCarpetaSolapa ordenCarpetaSolapa = ordenCarpetaSolapaService.crear(ordenTrabajo, carpetaSolapa);
        return "redirect:/mostrar-odt-carpeta-solapa/" + ordenCarpetaSolapa.getId();
    }
}
