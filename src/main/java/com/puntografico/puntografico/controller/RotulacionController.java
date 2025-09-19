package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.dto.RotulacionDTO;
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
public class RotulacionController {

    private final OpcionesRotulacionService opcionesRotulacionService;
    private final MedioPagoService medioPagoService;
    private final OrdenTrabajoService ordenTrabajoService;
    private final RotulacionService rotulacionService;
    private final OrdenRotulacionService ordenRotulacionService;
    private final ProductoService productoService;

    @GetMapping({"/crear-odt-rotulacion", "/crear-odt-rotulacion/{idOrden}"})
    public String verCrearOdtRotulacion(Model model, HttpSession session, @PathVariable(required = false) Long idOrden) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        OrdenRotulacion ordenRotulacion = (idOrden != null) ? ordenRotulacionService.buscarPorOrdenId(idOrden) : null;
        OrdenTrabajo ordenTrabajo = (ordenRotulacion != null) ? ordenRotulacion.getOrdenTrabajo() : new OrdenTrabajo();
        Rotulacion rotulacion = (ordenRotulacion != null) ? ordenRotulacion.getRotulacion() : new Rotulacion();

        List<TipoRotulacion> listaTipoRotulacion = opcionesRotulacionService.buscarTodosTipoRotulacion();
        List<TipoCorteRotulacion> listaTipoCorteRotulacion = opcionesRotulacionService.buscarTodosTipoCorteRotulacion();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("rotulacion", rotulacion);
        model.addAttribute("empleado", empleado);
        model.addAttribute("ordenTrabajo", ordenTrabajo);
        model.addAttribute("listaTipoRotulacion", listaTipoRotulacion);
        model.addAttribute("listaTipoCorteRotulacion", listaTipoCorteRotulacion);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-rotulacion";
    }

    @GetMapping("/mostrar-odt-rotulacion/{ordenRotulacionId}")
    public String verOrdenRotulacion(@PathVariable("ordenRotulacionId") Long ordenRotulacionId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenRotulacion ordenRotulacion = ordenRotulacionService.buscarPorId(ordenRotulacionId);

        model.addAttribute("ordenRotulacion", ordenRotulacion);

        return "mostrar-odt-rotulacion";
    }

    @PostMapping("/api/creacion-rotulacion")
    public String creacionProducto(HttpServletRequest request) {
        Long idOrden = productoService.buscarOrdenIdSiExiste(request.getParameter("idOrden"));

        OrdenRotulacion ordenRotulacionExistente = (idOrden != null) ? ordenRotulacionService.buscarPorOrdenId(idOrden) : null;
        Long idOrdenTrabajo = (ordenRotulacionExistente != null) ? ordenRotulacionExistente.getOrdenTrabajo().getId() : null;
        Long idRotulacion = (ordenRotulacionExistente != null) ? ordenRotulacionExistente.getRotulacion().getId() : null;
        Long idOrdenRotulacion = (ordenRotulacionExistente != null) ? ordenRotulacionExistente.getId() : null;

        RotulacionDTO rotulacionDTO = armarRotulacionDTO(request);

        OrdenTrabajo ordenTrabajo = ordenTrabajoService.guardar(request, idOrdenTrabajo);
        Rotulacion rotulacion = rotulacionService.guardar(rotulacionDTO, idRotulacion);
        OrdenRotulacion ordenRotulacion = ordenRotulacionService.guardar(ordenTrabajo, rotulacion, idOrdenRotulacion);

        return "redirect:/mostrar-odt-rotulacion/" + ordenRotulacion.getId();
    }

    private RotulacionDTO armarRotulacionDTO(HttpServletRequest request) {
        RotulacionDTO rotulacionDTO = new RotulacionDTO();
        rotulacionDTO.setEsLaminado(request.getParameter("esLaminado") != null);
        rotulacionDTO.setHorarioRotulacion(request.getParameter("horarioRotulacion"));
        rotulacionDTO.setDireccionRotulacion(request.getParameter("direccionRotulacion"));
        rotulacionDTO.setMedida(request.getParameter("medida"));
        rotulacionDTO.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        rotulacionDTO.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        rotulacionDTO.setInformacionAdicional(request.getParameter("informacionAdicional"));
        rotulacionDTO.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        rotulacionDTO.setTipoRotulacionId(Long.parseLong(request.getParameter("tipoRotulacion.id")));
        rotulacionDTO.setTipoCorteRotulacionId(Long.parseLong(request.getParameter("tipoCorteRotulacion.id")));

        return rotulacionDTO;
    }
}
