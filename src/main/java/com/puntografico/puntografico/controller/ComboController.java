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
public class ComboController {

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenComboService ordenComboService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private OpcionesComboService opcionesComboService;

    @Autowired
    private ComboService comboService;

    @GetMapping("/crear-odt-combo")
    public String verCrearOdtCombo(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();
        List<TipoCombo> listaTipoCombo = opcionesComboService.buscarTodosTipoCombo();

        model.addAttribute("combo", new Combo());
        model.addAttribute("listaMediosDePago", listaMediosDePago);
        model.addAttribute("listaTipoCombo", listaTipoCombo);

        return "crear-odt-combo";
    }

    @GetMapping("/mostrar-odt-combo/{ordenComboId}")
    public String verOrdenCombo(@PathVariable("ordenComboId") Long ordenComboId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenCombo ordenCombo = ordenComboService.buscarPorId(ordenComboId);

        model.addAttribute("ordenCombo", ordenCombo);

        return "mostrar-odt-combo";
    }

    @PostMapping("/api/creacion-combo")
    public String creacionCombo(HttpServletRequest request) {
        /*OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Combo combo = comboService.crear(request);
        OrdenCombo ordenCombo = ordenComboService.crear(ordenTrabajo, combo);

        return "redirect:/mostrar-odt-combo/" + ordenCombo.getId();*/

        return null;
    }
}
