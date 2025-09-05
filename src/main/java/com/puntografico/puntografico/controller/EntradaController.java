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
public class EntradaController {

    @Autowired
    private OpcionesEntradaService opcionesEntradaService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private EntradaService entradaService;

    @Autowired
    private OrdenEntradaService ordenEntradaService;

    @GetMapping("/crear-odt-entrada")
    public String verCrearOdtEntrada(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<TipoPapelEntrada> listaTipoPapelEntrada = opcionesEntradaService.buscarTodosTipoPapelEntrada();
        List<TipoColorEntrada> listaTipoColorEntrada = opcionesEntradaService.buscarTodosTipoColorEntrada();
        List<TipoTroqueladoEntrada> listaTipoTroqueladoEntrada = opcionesEntradaService.buscarTodosTipoTroqueladoEntrada();
        List<MedidaEntrada> listaMedidaEntrada = opcionesEntradaService.buscarTodosMedidaEntrada();
        List<CantidadEntrada> listaCantidadEntrada = opcionesEntradaService.buscarTodosCantidadEntrada();
        List<NumeradoEntrada> listaNumeradoEntrada = opcionesEntradaService.buscarTodosNumeradoEntrada();
        List<TerminacionEntrada> listaTerminacionEntrada = opcionesEntradaService.buscarTodosTerminacionEntrada();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("entrada", new Entrada());
        model.addAttribute("listaTipoPapelEntrada", listaTipoPapelEntrada);
        model.addAttribute("listaTipoColorEntrada", listaTipoColorEntrada);
        model.addAttribute("listaTipoTroqueladoEntrada", listaTipoTroqueladoEntrada);
        model.addAttribute("listaMedidaEntrada", listaMedidaEntrada);
        model.addAttribute("listaCantidadEntrada", listaCantidadEntrada);
        model.addAttribute("listaNumeradoEntrada", listaNumeradoEntrada);
        model.addAttribute("listaTerminacionEntrada", listaTerminacionEntrada);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-entrada";
    }

    @GetMapping("/mostrar-odt-entrada/{ordenEntradaId}")
    public String verOrdenEntrada(@PathVariable("ordenEntradaId") Long ordenEntradaId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenEntrada ordenEntrada = ordenEntradaService.buscarPorId(ordenEntradaId);

        model.addAttribute("ordenEntrada", ordenEntrada);

        return "mostrar-odt-entrada";
    }

    @PostMapping("/api/creacion-entrada")
    public String creacionEntrada(HttpServletRequest request) {
        /*OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Entrada entrada = entradaService.crear(request);
        OrdenEntrada ordenEntrada = ordenEntradaService.crear(ordenTrabajo, entrada);

        return "redirect:/mostrar-odt-entrada/" + ordenEntrada.getId();*/
        return null;
    }
}
