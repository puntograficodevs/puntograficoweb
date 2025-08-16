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
public class TalonarioController {

    @Autowired
    private TalonarioService talonarioService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OpcionesTalonarioService opcionesTalonarioService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private OrdenTalonarioService ordenTalonarioService;

    @GetMapping("/crear-odt-talonario")
    public String verCrearOdtTalonario(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<TipoTalonario> listaTipoTalonario = opcionesTalonarioService.buscarTodosTipoTalonario();
        List<TipoTroqueladoTalonario> listaTipoTroqueladoTalonario = opcionesTalonarioService.buscarTodosTipoTroqueladoTalonario();
        List<ModoTalonario> listaModoTalonario = opcionesTalonarioService.buscarTodosModoTalonario();
        List<TipoColorTalonario> listaTipoColorTalonario = opcionesTalonarioService.buscarTodosTipoColorTalonario();
        List<MedidaTalonario> listaMedidaTalonario = opcionesTalonarioService.buscarTodosMedidaTalonario();
        List<TipoPapelTalonario> listaTipoPapelTalonario = opcionesTalonarioService.buscarTodosTipoPapelTalonario();
        List<CantidadTalonario> listaCantidadTalonario = opcionesTalonarioService.buscarTodosCantidadTalonario();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("talonario", new Talonario());
        model.addAttribute("listaTipoTalonario", listaTipoTalonario);
        model.addAttribute("listaTipoTroqueladoTalonario", listaTipoTroqueladoTalonario);
        model.addAttribute("listaModoTalonario", listaModoTalonario);
        model.addAttribute("listaTipoColorTalonario", listaTipoColorTalonario);
        model.addAttribute("listaMedidaTalonario", listaMedidaTalonario);
        model.addAttribute("listaTipoPapelTalonario", listaTipoPapelTalonario);
        model.addAttribute("listaCantidadTalonario", listaCantidadTalonario);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-talonario";
    }

    @GetMapping("/mostrar-odt-talonario/{ordenTalonarioId}")
    public String verOrdenTalonario(@PathVariable("ordenTalonarioId") Long ordenTalonarioId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenTalonario ordenTalonario = ordenTalonarioService.buscarPorId(ordenTalonarioId);

        model.addAttribute("ordenTalonario", ordenTalonario);

        return "mostrar-odt-talonario";
    }

    @PostMapping("/api/creacion-talonario")
    public String creacionTalonario(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Talonario talonario = talonarioService.crear(request);
        OrdenTalonario ordenTalonario = ordenTalonarioService.crear(ordenTrabajo, talonario);
        return "redirect:/mostrar-odt-talonario/" + ordenTalonario.getId();
    }
}
