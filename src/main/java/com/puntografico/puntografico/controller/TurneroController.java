package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.dto.TurneroDTO;
import com.puntografico.puntografico.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller @AllArgsConstructor
public class TurneroController {

    private final OpcionesTurneroService opcionesTurneroService;
    private final MedioPagoService medioPagoService;
    private final OrdenTrabajoService ordenTrabajoService;
    private final TurneroService turneroService;
    private final OrdenTurneroService ordenTurneroService;
    private final ProductoService productoService;

    @GetMapping({"/crear-odt-turnero", "/crear-odt-turnero/{idOrden}"})
    public String verCreadOdtTurnero(Model model, HttpSession session, @PathVariable(required = false) Long idOrden) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        OrdenTurnero ordenTurnero = (idOrden != null) ? ordenTurneroService.buscarPorOrdenId(idOrden) : null;
        OrdenTrabajo ordenTrabajo = (ordenTurnero != null) ? ordenTurnero.getOrdenTrabajo() : new OrdenTrabajo();
        Turnero turnero = (ordenTurnero != null) ? ordenTurnero.getTurnero() : new Turnero();

        List<MedidaTurnero> listaMedidaTurnero = opcionesTurneroService.buscarTodosMedidaTurnero();
        List<TipoColorTurnero> listaTipoColorTurnero = opcionesTurneroService.buscarTodosTipoColorTurnero();
        List<CantidadTurnero> listaCantidadTurnero = opcionesTurneroService.buscarTodosCantidadTurnero();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("turnero", turnero);
        model.addAttribute("empleado", empleado);
        model.addAttribute("ordenTrabajo", ordenTrabajo);
        model.addAttribute("listaMedidaTurnero", listaMedidaTurnero);
        model.addAttribute("listaTipoColorTurnero", listaTipoColorTurnero);
        model.addAttribute("listaCantidadTurnero", listaCantidadTurnero);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-turnero";
    }

    @GetMapping("/mostrar-odt-turnero/{ordenTurneroId}")
    public String verOrdenTurnero(@PathVariable("ordenTurneroId") Long ordenTurneroId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenTurnero ordenTurnero = ordenTurneroService.buscarPorId(ordenTurneroId);

        model.addAttribute("ordenTurnero", ordenTurnero);

        return "mostrar-odt-turnero";
    }

    @PostMapping("/api/creacion-turnero")
    public String creacionProducto(HttpServletRequest request) {
        Long idOrden = productoService.buscarOrdenIdSiExiste(request.getParameter("idOrden"));

        OrdenTurnero ordenTurneroExistente = (idOrden != null) ? ordenTurneroService.buscarPorOrdenId(idOrden) : null;
        Long idOrdenTrabajo = (ordenTurneroExistente != null) ? ordenTurneroExistente.getOrdenTrabajo().getId() : null;
        Long idTurnero = (ordenTurneroExistente != null) ? ordenTurneroExistente.getTurnero().getId() : null;
        Long idOrdenTurnero = (ordenTurneroExistente != null) ? ordenTurneroExistente.getId() : null;

        TurneroDTO turneroDTO = armarTurneroDTO(request);

        OrdenTrabajo ordenTrabajo = ordenTrabajoService.guardar(request, idOrdenTrabajo);
        Turnero turnero = turneroService.guardar(turneroDTO, idTurnero);
        OrdenTurnero ordenTurnero = ordenTurneroService.guardar(ordenTrabajo, turnero, idOrdenTurnero);

        return "redirect:/mostrar-odt-turnero/" + ordenTurnero.getId();
    }

    private TurneroDTO armarTurneroDTO(HttpServletRequest request) {
        TurneroDTO turneroDTO = new TurneroDTO();
        turneroDTO.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        turneroDTO.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        turneroDTO.setCantidadHojas(Integer.parseInt(request.getParameter("cantidadHojas")));
        turneroDTO.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        turneroDTO.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        turneroDTO.setInformacionAdicional(request.getParameter("informacionAdicional"));
        turneroDTO.setTipoColorTurneroId(Long.parseLong(request.getParameter("tipoColorTurnero.id")));
        turneroDTO.setCantidadTurneroId(Long.parseLong(request.getParameter("cantidadTurnero.id")));
        turneroDTO.setMedidaTurneroId(Long.parseLong(request.getParameter("medidaTurnero.id")));

        return turneroDTO;
    }

    @DeleteMapping("/api/eliminar-orden-turnero/{idOrden}")
    public void eliminarOrdenTurnero(Model model, HttpSession session, @PathVariable Long idOrden) {
        OrdenTurnero ordenTurnero = ordenTurneroService.buscarPorOrdenId(idOrden);

        ordenTurneroService.eliminar(ordenTurnero.getId());
        ordenTrabajoService.eliminar(ordenTurnero.getOrdenTrabajo().getId());
        turneroService.eliminar(ordenTurnero.getTurnero().getId());
    }
}
