package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.dto.RifasBonosContribucionDTO;
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
public class RifasBonosContribucionController {

    private final OpcionesRifasContribucionService opcionesRifasContribucionService;
    private final MedioPagoService medioPagoService;
    private final OrdenTrabajoService ordenTrabajoService;
    private final RifasBonosContribucionService rifasBonosContribucionService;
    private final OrdenRifasBonosContribucionService ordenRifasBonosContribucionService;
    private final ProductoService productoService;

    @GetMapping({"/crear-odt-rifas-bonos-contribucion", "/crear-odt-rifas-bonos-contribucion/{idOrden}"})
    public String verCrearOdtRifasBonosContribucion(Model model, HttpSession session, @PathVariable(required = false) Long idOrden) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        OrdenRifasBonosContribucion ordenRifasBonosContribucion = (idOrden != null) ? ordenRifasBonosContribucionService.buscarPorOrdenId(idOrden) : null;
        OrdenTrabajo ordenTrabajo = (ordenRifasBonosContribucion != null) ? ordenRifasBonosContribucion.getOrdenTrabajo() : new OrdenTrabajo();
        RifasBonosContribucion rifasBonosContribucion = (ordenRifasBonosContribucion != null) ? ordenRifasBonosContribucion.getRifasBonosContribucion() : new RifasBonosContribucion();

        List<TipoPapelRifa> listaTipoPapelRifa = opcionesRifasContribucionService.buscarTodosTipoPapelRifa();
        List<TipoTroqueladoRifa> listaTipoTroqueladoRifa = opcionesRifasContribucionService.buscarTodosTipoTroqueladoRifa();
        List<TipoColorRifa> listaTipoColorRifa = opcionesRifasContribucionService.buscarTodosTipoColorRifa();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("rifasBonosContribucion", rifasBonosContribucion);
        model.addAttribute("empleado", empleado);
        model.addAttribute("ordenTrabajo", ordenTrabajo);
        model.addAttribute("listaTipoPapelRifa", listaTipoPapelRifa);
        model.addAttribute("listaTipoTroqueladoRifa", listaTipoTroqueladoRifa);
        model.addAttribute("listaTipoColorRifa", listaTipoColorRifa);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-rifas-bonos-contribucion";
    }

    @GetMapping("/mostrar-odt-rifas-bonos-contribucion/{ordenRifasBonosContribucionId}")
    public String verOrdenRifasBonosContribucion(@PathVariable("ordenRifasBonosContribucionId") Long ordenRifasBonosContribucionId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenRifasBonosContribucion ordenRifasBonosContribucion = ordenRifasBonosContribucionService.buscarPorId(ordenRifasBonosContribucionId);

        model.addAttribute("ordenRifasBonosContribucion", ordenRifasBonosContribucion);

        return "mostrar-odt-rifas-bonos-contribucion";
    }

    @PostMapping("/api/creacion-rifas-bonos-contribucion")
    public String creacionProducto(HttpServletRequest request) {
        Long idOrden = productoService.buscarOrdenIdSiExiste(request.getParameter("idOrden"));

        OrdenRifasBonosContribucion ordenRifasBonosContribucionExistente = (idOrden != null) ? ordenRifasBonosContribucionService.buscarPorOrdenId(idOrden) : null;
        Long idOrdenTrabajo = (ordenRifasBonosContribucionExistente != null) ? ordenRifasBonosContribucionExistente.getOrdenTrabajo().getId() : null;
        Long idRifasBonosContribucion = (ordenRifasBonosContribucionExistente != null) ? ordenRifasBonosContribucionExistente.getRifasBonosContribucion().getId() : null;
        Long idOrdenRifasBonosContribucion = (ordenRifasBonosContribucionExistente != null) ? ordenRifasBonosContribucionExistente.getId() : null;

        RifasBonosContribucionDTO rifasBonosContribucionDTO = armarRifasBonosContribucionDTO(request);

        OrdenTrabajo ordenTrabajo = ordenTrabajoService.guardar(request, idOrdenTrabajo);
        RifasBonosContribucion rifasBonosContribucion = rifasBonosContribucionService.guardar(rifasBonosContribucionDTO, idRifasBonosContribucion);
        OrdenRifasBonosContribucion ordenRifasBonosContribucion = ordenRifasBonosContribucionService.guardar(ordenTrabajo, rifasBonosContribucion, idOrdenRifasBonosContribucion);

        return "redirect:/mostrar-odt-rifas-bonos-contribucion/" + ordenRifasBonosContribucion.getId();
    }

    private RifasBonosContribucionDTO armarRifasBonosContribucionDTO(HttpServletRequest request) {
        RifasBonosContribucionDTO rifasBonosContribucionDTO = new RifasBonosContribucionDTO();
        rifasBonosContribucionDTO.setConNumerado(request.getParameter("conNumerado") != null);
        rifasBonosContribucionDTO.setDetalleNumerado(request.getParameter("detalleNumerado"));
        rifasBonosContribucionDTO.setConEncolado(request.getParameter("conEncolado") != null);
        rifasBonosContribucionDTO.setMedida(request.getParameter("medida"));
        rifasBonosContribucionDTO.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        rifasBonosContribucionDTO.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        rifasBonosContribucionDTO.setInformacionAdicional(request.getParameter("informacionAdicional"));
        rifasBonosContribucionDTO.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        rifasBonosContribucionDTO.setTipoPapelRifaId(Long.parseLong(request.getParameter("tipoPapelRifa.id")));
        rifasBonosContribucionDTO.setTipoTroqueladoRifaId(Long.parseLong(request.getParameter("tipoTroqueladoRifa.id")));
        rifasBonosContribucionDTO.setTipoColorRifaId(Long.parseLong(request.getParameter("tipoColorRifa.id")));

        return rifasBonosContribucionDTO;
    }
}
