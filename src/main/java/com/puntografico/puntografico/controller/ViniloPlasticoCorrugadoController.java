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
public class ViniloPlasticoCorrugadoController {

    @Autowired
    private OpcionesViniloPlasticoCorrugadoService opcionesViniloPlasticoCorrugadoService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private ViniloPlasticoCorrugadoService viniloPlasticoCorrugadoService;

    @Autowired
    private OrdenViniloPlasticoCorrugadoService ordenViniloPlasticoCorrugadoService;

    @GetMapping("/crear-odt-vinilo-plastico-corrugado")
    public String verCrearOdtViniloPlasticoCorrugado(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<MedidaViniloPlasticoCorrugado> listaMedidaViniloPlasticoCorrugado = opcionesViniloPlasticoCorrugadoService.buscarTodosMedidaViniloPlasticoCorrugado();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("viniloPlasticoCorrugado", new ViniloPlasticoCorrugado());
        model.addAttribute("listaMedidaViniloPlasticoCorrugado", listaMedidaViniloPlasticoCorrugado);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-vinilo-plastico-corrugado";
    }

    @GetMapping("/mostrar-odt-vinilo-plastico-corrugado/{ordenViniloPlasticoCorrugadoId}")
    public String verOrdenViniloPlasticoCorrugado(@PathVariable("ordenViniloPlasticoCorrugadoId") Long ordenViniloPlasticoCorrugadoId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenViniloPlasticoCorrugado ordenViniloPlasticoCorrugado = ordenViniloPlasticoCorrugadoService.buscarPorId(ordenViniloPlasticoCorrugadoId);

        model.addAttribute("ordenViniloPlasticoCorrugado", ordenViniloPlasticoCorrugado);

        return "mostrar-odt-vinilo-plastico-corrugado";
    }

    @PostMapping("/api/creacion-vinilo-plastico-corrugado")
    public String creacionProducto(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        ViniloPlasticoCorrugado viniloPlasticoCorrugado = viniloPlasticoCorrugadoService.crear(request);
        OrdenViniloPlasticoCorrugado ordenViniloPlasticoCorrugado = ordenViniloPlasticoCorrugadoService.crear(ordenTrabajo, viniloPlasticoCorrugado);

        return "redirect:/mostrar-odt-vinilo-plastico-corrugado/" + ordenViniloPlasticoCorrugado.getId();
    }
}
