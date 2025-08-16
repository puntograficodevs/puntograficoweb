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
public class VoucherController {

    @Autowired
    private OpcionesVoucherService opcionesVoucherService;

    @Autowired
    private MedioPagoService medioPagoService;

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private OrdenVoucherService ordenVoucherService;

    @GetMapping("/crear-odt-voucher")
    public String verCrearOdtVoucher(Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        List<MedidaVoucher> listaMedidaVoucher = opcionesVoucherService.buscarTodosMedidaVoucher();
        List<TipoPapelVoucher> listaTipoPapelVoucher = opcionesVoucherService.buscarTodosTipoPapelVoucher();
        List<TipoFazVoucher> listaTipoFazVoucher = opcionesVoucherService.buscarTodosTipoFazVoucher();
        List<CantidadVoucher> listaCantidadVoucher = opcionesVoucherService.buscarTodosCantidadVoucher();
        List<MedioPago> listaMediosDePago = medioPagoService.buscarTodos();

        model.addAttribute("voucher", new Voucher());
        model.addAttribute("listaMedidaVoucher", listaMedidaVoucher);
        model.addAttribute("listaTipoPapelVoucher", listaTipoPapelVoucher);
        model.addAttribute("listaTipoFazVoucher", listaTipoFazVoucher);
        model.addAttribute("listaCantidadVoucher", listaCantidadVoucher);
        model.addAttribute("listaMediosDePago", listaMediosDePago);

        return "crear-odt-voucher";
    }

    @GetMapping("/mostrar-odt-voucher/{ordenVoucherId}")
    public String verOrdenVoucher(@PathVariable("ordenVoucherId") Long ordenVoucherId, Model model, HttpSession session) {
        Empleado empleado = (Empleado) session.getAttribute("empleadoLogueado");

        if (empleado == null) {
            return "redirect:/"; // Si no hay sesión, lo manda al login
        }

        model.addAttribute("empleado", empleado);

        OrdenVoucher ordenVoucher = ordenVoucherService.buscarPorId(ordenVoucherId);

        model.addAttribute("ordenVoucher", ordenVoucher);

        return "mostrar-odt-voucher";
    }

    @PostMapping("/api/creacion-voucher")
    public String creacionProducto(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoService.crear(request);
        Voucher voucher = voucherService.crear(request);
        OrdenVoucher ordenVoucher = ordenVoucherService.crear(ordenTrabajo, voucher);

        return "redirect:/mostrar-odt-voucher/" + ordenVoucher.getId();
    }
}
