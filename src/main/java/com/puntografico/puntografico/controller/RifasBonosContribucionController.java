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
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RifasBonosContribucionController {

    @Autowired
    private OpcionesRifasContribucionService opcionesRifasContribucionService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private RifasBonosContribucionService rifasBonosContribucionService;

    @Autowired
    private OrdenRifasBonosContribucionService ordenRifasBonosContribucionService;

    @GetMapping("/crear-odt-rifas-bonos-contribucion")
    public String verCrearOdtRifasBonosContribucion(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<TipoPapelRifa> listaTipoPapelRifa = opcionesRifasContribucionService.buscarTodosTipoPapelRifa();
        List<TipoTroqueladoRifa> listaTipoTroqueladoRifa = opcionesRifasContribucionService.buscarTodosTipoTroqueladoRifa();
        List<TipoColorRifa> listaTipoColorRifa = opcionesRifasContribucionService.buscarTodosTipoColorRifa();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("rifasBonosContribucion", new RifasBonosContribucion());
        model.addAttribute("listaTipoPapelRifa", listaTipoPapelRifa);
        model.addAttribute("listaTipoTroqueladoRifa", listaTipoTroqueladoRifa);
        model.addAttribute("listaTipoColorRifa", listaTipoColorRifa);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-rifas-bonos-contribucion";
    }

    @GetMapping("/mostrar-odt-rifas-bonos-contribucion/{ordenRifasBonosContribucionId}")
    public String verOrdenRifasBonosContribucion(@PathVariable("ordenRifasBonosContribucionId") Long ordenRifasBonosContribucionId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenRifasBonosContribucion ordenRifasBonosContribucion = ordenRifasBonosContribucionService.buscarPorId(ordenRifasBonosContribucionId);

        model.addAttribute("ordenRifasBonosContribucion", ordenRifasBonosContribucion);

        return "mostrar-odt-rifas-bonos-contribucion";
    }

    @PostMapping("/api/creacion-rifas-bonos-contribucion")
    public String creacionProducto(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        RifasBonosContribucion rifasBonosContribucion = rifasBonosContribucionService.crear(request);
        OrdenRifasBonosContribucion ordenRifasBonosContribucion = ordenRifasBonosContribucionService.crear(ordenTrabajo, rifasBonosContribucion);

        return "redirect:/mostrar-odt-rifas-bonos-contribucion/" + ordenRifasBonosContribucion.getId();
    }

}
