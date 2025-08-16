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
public class FolletoController {

    @Autowired
    private OpcionesFolletoService opcionesFolletoService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private FolletoService folletoService;

    @Autowired
    private OrdenFolletoService ordenFolletoService;

    @GetMapping("/crear-odt-folleto")
    public String verCrearOdtFolleto(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<TipoPapelFolleto> listaTipoPapelFolleto = opcionesFolletoService.buscarTodosTipoPapelFolleto();
        List<TipoColorFolleto> listaTipoColorFolleto = opcionesFolletoService.buscarTodosTipoColorFolleto();
        List<TipoFazFolleto> listaTipoFazFolleto = opcionesFolletoService.buscarTodosTipoFazFolleto();
        List<TamanioHojaFolleto> listaTamanioHojaFolleto = opcionesFolletoService.buscarTodosTamanioHojaFolleto();
        List<TipoFolleto> listaTipoFolleto = opcionesFolletoService.buscarTodosTipoFolleto();
        List<CantidadFolleto> listaCantidadFolleto = opcionesFolletoService.buscarTodosCantidadFolleto();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("folleto", new Folleto());
        model.addAttribute("listaTipoPapelFolleto", listaTipoPapelFolleto);
        model.addAttribute("listaTipoColorFolleto", listaTipoColorFolleto);
        model.addAttribute("listaTipoFazFolleto", listaTipoFazFolleto);
        model.addAttribute("listaTamanioHojaFolleto", listaTamanioHojaFolleto);
        model.addAttribute("listaTipoFolleto", listaTipoFolleto);
        model.addAttribute("listaCantidadFolleto", listaCantidadFolleto);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-folleto";
    }

    @GetMapping("/mostrar-odt-folleto/{ordenFolletoId}")
    public String verOrdenFolleto(@PathVariable("ordenFolletoId") Long ordenFolletoId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenFolleto ordenFolleto = ordenFolletoService.buscarPorId(ordenFolletoId);

        model.addAttribute("ordenFolleto", ordenFolleto);

        return "mostrar-odt-folleto";
    }

    @PostMapping("/api/creacion-folleto")
    public String creacionProducto(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Folleto folleto = folletoService.crear(request);
        OrdenFolleto ordenFolleto = ordenFolletoService.crear(ordenTrabajo, folleto);

        return "redirect:/mostrar-odt-folleto/" + ordenFolleto.getId();
    }
}
