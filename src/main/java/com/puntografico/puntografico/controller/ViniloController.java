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
public class ViniloController {

    @Autowired
    private OpcionesViniloService opcionesViniloService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private ViniloService viniloService;

    @Autowired
    private OrdenViniloService ordenViniloService;

    @GetMapping("/crear-odt-vinilo")
    public String verCrearOdtVinilo(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<TipoVinilo> listaTipoVinilo = opcionesViniloService.buscarTodosTipoVinilo();
        List<TipoAdicionalVinilo> listaTipoAdicionalVinilo = opcionesViniloService.buscarTodosTipoAdicionalVinilo();
        List<TipoCorteVinilo> listaTipoCorteVinilo = opcionesViniloService.buscarTodosTipoCorteVinilo();
        List<MedidaVinilo> listaMedidaVinilo = opcionesViniloService.buscarTodosMedidaVinilo();
        List<CantidadVinilo> listaCantidadVinilo = opcionesViniloService.buscarTodosCantidadVinilo();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("vinilo", new Vinilo());
        model.addAttribute("listaTipoVinilo", listaTipoVinilo);
        model.addAttribute("listaTipoAdicionalVinilo", listaTipoAdicionalVinilo);
        model.addAttribute("listaTipoCorteVinilo", listaTipoCorteVinilo);
        model.addAttribute("listaMedidaVinilo", listaMedidaVinilo);
        model.addAttribute("listaCantidadVinilo", listaCantidadVinilo);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-vinilo";
    }

    @GetMapping("/mostrar-odt-vinilo/{ordenViniloId}")
    public String verOrdenVinilo(@PathVariable("ordenViniloId") Long ordenViniloId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenVinilo ordenVinilo = ordenViniloService.buscarPorId(ordenViniloId);

        model.addAttribute("ordenVinilo", ordenVinilo);

        return "mostrar-odt-vinilo";
    }

    @PostMapping("/api/creacion-vinilo")
    public String creacionProducto(HttpServletRequest request) {
        /*OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Vinilo vinilo = viniloService.crear(request);
        OrdenVinilo ordenVinilo = ordenViniloService.crear(ordenTrabajo, vinilo);

        return "redirect:/mostrar-odt-vinilo/" + ordenVinilo.getId();*/

        return null;
    }
}
