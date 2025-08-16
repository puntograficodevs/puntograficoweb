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
public class LonaComunController {

    @Autowired
    private OpcionesLonaComunService opcionesLonaComunService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private LonaComunService lonaComunService;

    @Autowired
    private OrdenLonaComunService ordenLonaComunService;

    @GetMapping("/crear-odt-lona-comun")
    public String verCrearOdtLonaComun(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<MedidaLonaComun> listaMedidaLonaComun = opcionesLonaComunService.buscarTodosMedidaLonaComun();
        List<TipoLonaComun> listaTipoLonaComun = opcionesLonaComunService.buscarTodosTipoLonaComun();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("lonaComun", new LonaComun());
        model.addAttribute("listaMedidaLonaComun", listaMedidaLonaComun);
        model.addAttribute("listaTipoLonaComun", listaTipoLonaComun);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-lona-comun";
    }

    @GetMapping("/mostrar-odt-lona-comun/{ordenLonaComunId}")
    public String verOrdenLonaComun(@PathVariable("ordenLonaComunId") Long ordenLonaComunId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenLonaComun ordenLonaComun = ordenLonaComunService.buscarPorId(ordenLonaComunId);

        model.addAttribute("ordenLonaComun", ordenLonaComun);

        return "mostrar-odt-lona-comun";
    }

    @PostMapping("/api/creacion-lona-comun")
    public String creacionProducto(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        LonaComun lonaComun = lonaComunService.crear(request);
        OrdenLonaComun ordenLonaComun = ordenLonaComunService.crear(ordenTrabajo, lonaComun);

        return "redirect:/mostrar-odt-lona-comun/" + ordenLonaComun.getId();
    }
}
