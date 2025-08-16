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
public class TurneroController {

    @Autowired
    private OpcionesTurneroService opcionesTurneroService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private TurneroService turneroService;

    @Autowired
    private OrdenTurneroService ordenTurneroService;

    @GetMapping("/crear-odt-turnero")
    public String verCreadOdtTurnero(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<MedidaTurnero> listaMedidaTurnero = opcionesTurneroService.buscarTodosMedidaTurnero();
        List<TipoColorTurnero> listaTipoColorTurnero = opcionesTurneroService.buscarTodosTipoColorTurnero();
        List<CantidadTurnero> listaCantidadTurnero = opcionesTurneroService.buscarTodosCantidadTurnero();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("turnero", new Turnero());
        model.addAttribute("listaMedidaTurnero", listaMedidaTurnero);
        model.addAttribute("listaTipoColorTurnero", listaTipoColorTurnero);
        model.addAttribute("listaCantidadTurnero", listaCantidadTurnero);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-turnero";
    }

    @GetMapping("/mostrar-odt-turnero/{ordenTurneroId}")
    public String verOrdenTurnero(@PathVariable("ordenTurneroId") Long ordenTurneroId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenTurnero ordenTurnero = ordenTurneroService.buscarPorId(ordenTurneroId);

        model.addAttribute("ordenTurnero", ordenTurnero);

        return "mostrar-odt-turnero";
    }

    @PostMapping("/api/creacion-turnero")
    public String creacionProducto(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Turnero turnero = turneroService.crear(request);
        OrdenTurnero ordenTurnero = ordenTurneroService.crear(ordenTrabajo, turnero);

        return "redirect:/mostrar-odt-turnero/" + ordenTurnero.getId();
    }
}
