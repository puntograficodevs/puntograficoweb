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
public class StickerController {

    @Autowired
    private StickerService stickerService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OpcionesStickerService opcionesStickerService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private OrdenStickerService ordenStickerService;

    @GetMapping("/crear-odt-sticker")
    public String verCrearOdtSticker(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<TipoTroqueladoSticker> listaTipoTroqueladoSticker = opcionesStickerService.buscarTodosTipoTroqueladoSticker();
        List<CantidadSticker> listaCantidadSticker = opcionesStickerService.buscarTodosCantidadSticker();
        List<MedidaSticker> listaMedidaSticker = opcionesStickerService.buscarTodosMedidaSticker();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("sticker", new Sticker());
        model.addAttribute("listaTipoTroqueladoSticker", listaTipoTroqueladoSticker);
        model.addAttribute("listaCantidadSticker", listaCantidadSticker);
        model.addAttribute("listaMedidaSticker", listaMedidaSticker);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-sticker";
    }

    @GetMapping("/mostrar-odt-sticker/{ordenStickerId}")
    public String verOrdenSticker(@PathVariable("ordenStickerId") Long ordenStickerId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenSticker ordenSticker = ordenStickerService.buscarPorId(ordenStickerId);

        model.addAttribute("ordenSticker", ordenSticker);

        return "mostrar-odt-sticker";
    }

    @PostMapping("/api/creacion-sticker")
    public String creacionSticker(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Sticker sticker = stickerService.crear(request);
        OrdenSticker ordenSticker = ordenStickerService.crear(ordenTrabajo, sticker);
        return "redirect:/mostrar-odt-sticker/" + ordenSticker.getId();
    }
}
