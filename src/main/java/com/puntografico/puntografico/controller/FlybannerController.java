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
public class FlybannerController {

    @Autowired
    private OpcionesFlybannerService opcionesFlybannerService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private FlybannerService flybannerService;

    @Autowired
    private OrdenFlybannerService ordenFlybannerService;

    @GetMapping("/crear-odt-flybanner")
    public String verCrearOdtFlybanner(Model model) {
        List<TipoFazFlybanner> listaTipoFazFlybanner = opcionesFlybannerService.buscarTodosTipoFazFlybaner();
        List<AlturaFlybanner> listaAlturaFlybanner = opcionesFlybannerService.buscarTodosAlturaFlybanner();
        List<BanderaFlybanner> listaBanderaFlybanner = opcionesFlybannerService.buscarTodosBanderaFlybanner();
        List<TipoBaseFlybanner> listaTipoBaseFlybanner = opcionesFlybannerService.buscarTodosTipoBaseFlybanner();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("flybanner", new Flybanner());
        model.addAttribute("listaTipoFazFlybanner", listaTipoFazFlybanner);
        model.addAttribute("listaAlturaFlybanner", listaAlturaFlybanner);
        model.addAttribute("listaBanderaFlybanner", listaBanderaFlybanner);
        model.addAttribute("listaTipoBaseFlybanner", listaTipoBaseFlybanner);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-flybanner";
    }

    @GetMapping("/mostrar-odt-flybanner/{ordenFlybannerId}")
    public String verOrdenFlybanner(@PathVariable("ordenFlybannerId") Long ordenFlybannerId, Model model) {
        OrdenFlybanner ordenFlybanner = ordenFlybannerService.buscarPorId(ordenFlybannerId);

        model.addAttribute("ordenFlybanner", ordenFlybanner);

        return "mostrar-odt-flybanner";
    }

    @PostMapping("/api/creacion-flybanner")
    public String creacionProducto(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Flybanner flybanner = flybannerService.crear(request);
        OrdenFlybanner ordenFlybanner = ordenFlybannerService.crear(ordenTrabajo, flybanner);

        return "redirect:/mostrar-odt-flybanner/" + ordenFlybanner.getId();
    }
}
