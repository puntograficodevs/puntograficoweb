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
public class ImpresionController {

    @Autowired
    private OpcionesImpresionService opcionesImpresionService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private ImpresionService impresionService;

    @Autowired
    private OrdenImpresionService ordenImpresionService;

    @GetMapping("/crear-odt-impresion")
    public String verCrearOdtImpresion(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<TipoColorImpresion> listaTipoColorImpresion = opcionesImpresionService.buscarTodosTipoColorImpresion();
        List<TamanioHojaImpresion> listaTamanioHojaImpresion = opcionesImpresionService.buscarTodosTamanioHojaImpresion();
        List<TipoFazImpresion> listaTipoFazImpresion = opcionesImpresionService.buscarTodosTipoFazImpresion();
        List<TipoPapelImpresion> listaTipoPapelImpresion = opcionesImpresionService.buscarTodosTipoPapelImpresion();
        List<CantidadImpresion> listaCantidadImpresion = opcionesImpresionService.buscarTodosCantidadImpresion();
        List<TipoImpresion> listaTipoImpresion = opcionesImpresionService.buscarTodosTipoImpresion();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("impresion", new Impresion());
        model.addAttribute("listaTipoColorImpresion", listaTipoColorImpresion);
        model.addAttribute("listaTamanioHojaImpresion", listaTamanioHojaImpresion);
        model.addAttribute("listaTipoFazImpresion", listaTipoFazImpresion);
        model.addAttribute("listaTipoPapelImpresion", listaTipoPapelImpresion);
        model.addAttribute("listaCantidadImpresion", listaCantidadImpresion);
        model.addAttribute("listaTipoImpresion", listaTipoImpresion);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-impresion";
    }

    @GetMapping("/mostrar-odt-impresion/{ordenImpresionId}")
    public String verOrdenImpresion(@PathVariable("ordenImpresionId") Long ordenImpresionId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenImpresion ordenImpresion = ordenImpresionService.buscarPorId(ordenImpresionId);

        model.addAttribute("ordenImpresion", ordenImpresion);

        return "mostrar-odt-impresion";
    }

    @PostMapping("/api/creacion-impresion")
    public String creacionImpresion(HttpServletRequest request) {
        /*OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Impresion impresion = impresionService.crear(request);
        OrdenImpresion ordenImpresion = ordenImpresionService.crear(ordenTrabajo, impresion);

        return "redirect:/mostrar-odt-impresion/" + ordenImpresion.getId();*/
        return null;
    }
}
