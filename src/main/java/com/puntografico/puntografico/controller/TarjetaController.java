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
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OpcionesTarjetaService opcionesTarjetaService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private OrdenTarjetaService ordenTarjetaService;

    @GetMapping("/crear-odt-tarjeta")
    public String verCrearOdtTarjeta(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<TipoPapelTarjeta> listaTipoPapelTarjeta = opcionesTarjetaService.buscarTodosTipoPapelTarjeta();
        List<TipoColorTarjeta> listaTipoColorTarjeta = opcionesTarjetaService.buscarTodosTipoColorTarjeta();
        List<TipoFazTarjeta> listaTipoFazTarjeta = opcionesTarjetaService.buscarTodosTipoFazTarjeta();
        List<TipoLaminadoTarjeta> listaTipoLaminadoTarjeta = opcionesTarjetaService.buscarTodosTipoLaminadoTarjeta();
        List<MedidaTarjeta> listaMedidaTarjeta = opcionesTarjetaService.buscarTodosMedidaTarjeta();
        List<CantidadTarjeta> listaCantidadTarjeta = opcionesTarjetaService.buscarTodosCantidadTarjeta();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("tarjeta", new Tarjeta());
        model.addAttribute("listaTipoPapelTarjeta", listaTipoPapelTarjeta);
        model.addAttribute("listaTipoColorTarjeta", listaTipoColorTarjeta);
        model.addAttribute("listaTipoFazTarjeta", listaTipoFazTarjeta);
        model.addAttribute("listaTipoLaminadoTarjeta", listaTipoLaminadoTarjeta);
        model.addAttribute("listaMedidaTarjeta", listaMedidaTarjeta);
        model.addAttribute("listaCantidadTarjeta", listaCantidadTarjeta);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-tarjeta";
    }

    @GetMapping("/mostrar-odt-tarjeta/{ordenTarjetaId}")
    public String verOrdenTarjeta(@PathVariable("ordenTarjetaId") Long ordenTarjetaId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenTarjeta ordenTarjeta = ordenTarjetaService.buscarPorId(ordenTarjetaId);

        model.addAttribute("ordenTarjeta", ordenTarjeta);

        return "mostrar-odt-tarjeta";
    }

    @PostMapping("/api/creacion-tarjeta")
    public String creacionTarjeta(HttpServletRequest request) {
        /*OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Tarjeta tarjeta = tarjetaService.crear(request);
        OrdenTarjeta ordenTarjeta = ordenTarjetaService.crear(ordenTrabajo, tarjeta);
        return "redirect:/mostrar-odt-tarjeta/" + ordenTarjeta.getId();*/
        return null;
    }
}
