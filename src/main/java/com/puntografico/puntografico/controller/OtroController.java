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
public class OtroController {

    @Autowired
    private OpcionesOtroService opcionesOtroService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private OtroService otroService;

    @Autowired
    private OrdenOtroService ordenOtroService;

    @GetMapping("/crear-odt-otro")
    public String verCrearOdtOtro(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<TipoColorOtro> listaTipoColorOtro = opcionesOtroService.buscarTodosTipoColorOtro();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("otro", new Otro());
        model.addAttribute("listaTipoColorOtro", listaTipoColorOtro);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-otro";
    }

    @GetMapping("/mostrar-odt-otro/{ordenOtroId}")
    public String verOrdenOtro(@PathVariable("ordenOtroId") Long ordenOtroId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenOtro ordenOtro = ordenOtroService.buscarPorId(ordenOtroId);

        model.addAttribute("ordenOtro", ordenOtro);

        return "mostrar-odt-otro";
    }

    @PostMapping("/api/creacion-otro")
    public String creacionProducto(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Otro otro = otroService.crear(request);
        OrdenOtro ordenOtro = ordenOtroService.crear(ordenTrabajo, otro);

        return "redirect:/mostrar-odt-otro/" + ordenOtro.getId();
    }
}
