package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller @AllArgsConstructor
public class FlybannerController {

    private final OpcionesFlybannerService opcionesFlybannerService;
    private final MedioPagoService medioPagoService;
    private final OrdenTrabajoService ordenTrabajoService;
    private final FlybannerService flybannerService;
    private final OrdenFlybannerService ordenFlybannerService;
    private final ProductoService productoService;

    @GetMapping({"/crear-odt-flybanner", "/crear-odt-flybanner/{idOrden}"})
    public String verCrearOdtFlybanner(Model model, HttpSession session, @PathVariable(required = false) Long idOrden) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        OrdenFlybanner ordenFlybanner = (idOrden != null) ? ordenFlybannerService.buscarPorOrdenId(idOrden) : null;
        OrdenTrabajo ordenTrabajo = (ordenFlybanner != null) ? ordenFlybanner.getOrdenTrabajo() : new OrdenTrabajo();
        Flybanner flybanner = (ordenFlybanner != null) ? ordenFlybanner.getFlybanner() : new Flybanner();

        List<TipoFazFlybanner> listaTipoFazFlybanner = opcionesFlybannerService.buscarTodosTipoFazFlybaner();
        List<AlturaFlybanner> listaAlturaFlybanner = opcionesFlybannerService.buscarTodosAlturaFlybanner();
        List<BanderaFlybanner> listaBanderaFlybanner = opcionesFlybannerService.buscarTodosBanderaFlybanner();
        List<TipoBaseFlybanner> listaTipoBaseFlybanner = opcionesFlybannerService.buscarTodosTipoBaseFlybanner();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("flybanner", flybanner);
        model.addAttribute("empleado", empleado);
        model.addAttribute("ordenTrabajo", ordenTrabajo);
        model.addAttribute("listaTipoFazFlybanner", listaTipoFazFlybanner);
        model.addAttribute("listaAlturaFlybanner", listaAlturaFlybanner);
        model.addAttribute("listaBanderaFlybanner", listaBanderaFlybanner);
        model.addAttribute("listaTipoBaseFlybanner", listaTipoBaseFlybanner);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-flybanner";
    }

    @GetMapping("/mostrar-odt-flybanner/{ordenFlybannerId}")
    public String verOrdenFlybanner(@PathVariable("ordenFlybannerId") Long ordenFlybannerId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenFlybanner ordenFlybanner = ordenFlybannerService.buscarPorId(ordenFlybannerId);

        model.addAttribute("ordenFlybanner", ordenFlybanner);

        return "mostrar-odt-flybanner";
    }

    @PostMapping("/api/creacion-flybanner")
    public String creacionProducto(HttpServletRequest request) {
        /*OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Flybanner flybanner = flybannerService.crear(request);
        OrdenFlybanner ordenFlybanner = ordenFlybannerService.crear(ordenTrabajo, flybanner);

        return "redirect:/mostrar-odt-flybanner/" + ordenFlybanner.getId();*/

        return null;
    }
}
