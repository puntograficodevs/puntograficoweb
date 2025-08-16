package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional
public class VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private MedidaVoucherRepository medidaVoucherRepository;

    @Autowired
    private TipoPapelVoucherRepository tipoPapelVoucherRepository;

    @Autowired
    private TipoFazVoucherRepository tipoFazVoucherRepository;

    @Autowired
    private CantidadVoucherRepository cantidadVoucherRepository;

    public Voucher crear(HttpServletRequest request) {
        String medidaVoucherString = request.getParameter("medidaVoucher.id");
        String tipoPapelVoucherString = request.getParameter("tipoPapelVoucher.id");
        String tipoFazVoucherString = request.getParameter("tipoFazVoucher.id");
        String cantidadVoucherString = request.getParameter("cantidadVoucher.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(medidaVoucherString, "medidaVoucherString es un dato obligatorio.");
        Assert.notNull(tipoPapelVoucherString, "tipoPapelVoucherString es un dato obligatorio.");
        Assert.notNull(tipoFazVoucherString, "tipoFazVoucherString es un dato obligatorio.");
        Assert.notNull(cantidadVoucherString, "cantidadVoucherString es un dato obligatorio.");

        MedidaVoucher medidaVoucher = medidaVoucherRepository.findById(Long.parseLong(medidaVoucherString)).get();
        TipoPapelVoucher tipoPapelVoucher = tipoPapelVoucherRepository.findById(Long.parseLong(tipoPapelVoucherString)).get();
        TipoFazVoucher tipoFazVoucher = tipoFazVoucherRepository.findById(Long.parseLong(tipoFazVoucherString)).get();
        CantidadVoucher cantidadVoucher = cantidadVoucherRepository.findById(Long.parseLong(cantidadVoucherString)).get();

        int cantidad;

        if (cantidadVoucher.getId() != 4) {
            cantidad = Integer.parseInt(cantidadVoucher.getCantidad());
        } else {
            cantidad = Integer.parseInt(cantidadString);
        }

        Voucher voucher = new Voucher();
        voucher.setTipoPapelPersonalizado(request.getParameter("tipoPapelPersonalizado"));
        voucher.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        voucher.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        voucher.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        voucher.setInformacionAdicional(request.getParameter("informacionAdicional"));
        voucher.setMedidaVoucher(medidaVoucher);
        voucher.setTipoPapelVoucher(tipoPapelVoucher);
        voucher.setTipoFazVoucher(tipoFazVoucher);
        voucher.setCantidadVoucher(cantidadVoucher);
        voucher.setCantidad(cantidad);

        return voucherRepository.save(voucher);
    }
}
