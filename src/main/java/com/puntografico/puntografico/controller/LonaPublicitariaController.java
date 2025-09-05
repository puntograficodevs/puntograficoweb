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
public class LonaPublicitariaController {
    @Autowired
    private OpcionesLonaPublicitariaService opcionesLonaPublicitariaService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private LonaPublicitariaService lonaPublicitariaService;

    @Autowired
    private OrdenLonaPublicitariaService ordenLonaPublicitariaService;

    @GetMapping("/crear-odt-lona-publicitaria")
    public String verCrearOdtLonaPublicitaria(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<MedidaLonaPublicitaria> listaMedidaLonaPublicitaria = opcionesLonaPublicitariaService.buscarTodosMedidaLonaPublicitaria();
        List<TipoLonaPublicitaria> listaTipoLonaPublicitaria = opcionesLonaPublicitariaService.buscarTodosTipoLonaPublicitaria();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("lonaPublicitaria", new LonaPublicitaria());
        model.addAttribute("listaMedidaLonaPublicitaria", listaMedidaLonaPublicitaria);
        model.addAttribute("listaTipoLonaPublicitaria", listaTipoLonaPublicitaria);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-lona-publicitaria";
    }

    @GetMapping("/mostrar-odt-lona-publicitaria/{ordenLonaPublicitariaId}")
    public String verOrdenLonaPublicitaria(@PathVariable("ordenLonaPublicitariaId") Long ordenLonaPublicitariaId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenLonaPublicitaria ordenLonaPublicitaria = ordenLonaPublicitariaService.buscarPorId(ordenLonaPublicitariaId);

        model.addAttribute("ordenLonaPublicitaria", ordenLonaPublicitaria);

        return "mostrar-odt-lona-publicitaria";
    }

    @PostMapping("/api/creacion-lona-publicitaria")
    public String creacionProducto(HttpServletRequest request) {
        /*OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        LonaPublicitaria lonaPublicitaria = lonaPublicitariaService.crear(request);
        OrdenLonaPublicitaria ordenLonaPublicitaria = ordenLonaPublicitariaService.crear(ordenTrabajo, lonaPublicitaria);

        return "redirect:/mostrar-odt-lona-publicitaria/" + ordenLonaPublicitaria.getId();*/
        return null;
    }
}
