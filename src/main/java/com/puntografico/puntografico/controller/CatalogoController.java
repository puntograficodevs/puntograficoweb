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
public class CatalogoController {

    @Autowired
    private OpcionesCatalogoService opcionesCatalogoService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenCatalogoService ordenCatalogoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping("/crear-odt-catalogo")
    public String verCrearOdtCatalogo(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<TipoFazCatalogo> listaTipoFazCatalogo = opcionesCatalogoService.buscarTodosTipoFazCatalogo();
        List<TipoLaminadoCatalogo> listaTipoLaminadoCatalogo = opcionesCatalogoService.buscarTodosTipoLaminadoCatalogo();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("catalogo", new Catalogo());
        model.addAttribute("listaTipoFazCatalogo", listaTipoFazCatalogo);
        model.addAttribute("listaTipoLaminadoCatalogo", listaTipoLaminadoCatalogo);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-catalogo";
    }

    @GetMapping("/mostrar-odt-catalogo/{ordenCatalogoId}")
    public String verOrdenCatalogo(@PathVariable("ordenCatalogoId") Long ordenCatalogoId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenCatalogo ordenCatalogo = ordenCatalogoService.buscarPorId(ordenCatalogoId);

        model.addAttribute("ordenCatalogo", ordenCatalogo);

        return "mostrar-odt-catalogo";
    }

    @PostMapping("/api/creacion-catalogo")
    public String creacionCatalogo(HttpServletRequest request) {
        /*OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Catalogo catalogo = catalogoService.crear(request);
        OrdenCatalogo ordenCatalogo = ordenCatalogoService.crear(ordenTrabajo, catalogo);
        return "redirect:/mostrar-odt-catalogo/" + ordenCatalogo.getId();*/

        return null;
    }
}
