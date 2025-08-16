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
public class CuadernoAnilladoController {

    @Autowired
    private OpcionesCuadernoAnilladoService opcionesCuadernoAnilladoService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private CuadernoAnilladoService cuadernoAnilladoService;

    @Autowired
    private OrdenCuadernoAnilladoService ordenCuadernoAnilladoService;

    @GetMapping("/crear-odt-cuaderno-anillado")
    public String verCrearOdtCuadernoAnillado(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<TipoTapaCuadernoAnillado> listaTipoTapaCuadernoAnillado = opcionesCuadernoAnilladoService.buscarTodosTipoTapaCuadernoAnillado();
        List<MedidaCuadernoAnillado> listaMedidaCuadernoAnillado = opcionesCuadernoAnilladoService.buscarTodosMedidaCuadernoAnillado();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("cuadernoAnillado", new CuadernoAnillado());
        model.addAttribute("listaTipoTapaCuadernoAnillado", listaTipoTapaCuadernoAnillado);
        model.addAttribute("listaMedidaCuadernoAnillado", listaMedidaCuadernoAnillado);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-cuaderno-anillado";
    }

    @GetMapping("/mostrar-odt-cuaderno-anillado/{ordenCuadernoAnilladoId}")
    public String verOrdenCuadernoAnillado(@PathVariable("ordenCuadernoAnilladoId") Long ordenCuadernoAnilladoId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenCuadernoAnillado ordenCuadernoAnillado = ordenCuadernoAnilladoService.buscarPorId(ordenCuadernoAnilladoId);

        model.addAttribute("ordenCuadernoAnillado", ordenCuadernoAnillado);

        return "mostrar-odt-cuaderno-anillado";
    }

    @PostMapping("/api/creacion-cuaderno-anillado")
    public String creacionCuadernoAnillado(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        CuadernoAnillado cuadernoAnillado = cuadernoAnilladoService.crear(request);
        OrdenCuadernoAnillado ordenCuadernoAnillado = ordenCuadernoAnilladoService.crear(ordenTrabajo, cuadernoAnillado);
        return "redirect:/mostrar-odt-cuaderno-anillado/" + ordenCuadernoAnillado.getId();
    }
}
