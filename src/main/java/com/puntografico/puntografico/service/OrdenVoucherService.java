package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Agenda;
import com.puntografico.puntografico.domain.OrdenVoucher;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.domain.Voucher;
import com.puntografico.puntografico.repository.OrdenVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenVoucherService {

    @Autowired
    private OrdenVoucherRepository ordenVoucherRepository;

    public OrdenVoucher crear(OrdenTrabajo ordenTrabajo, Voucher voucher) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(voucher, "Debe venir un voucher para enlazar.");

        OrdenVoucher ordenVoucher = new OrdenVoucher();
        ordenVoucher.setCantidad(voucher.getCantidad());
        ordenVoucher.setOrdenTrabajo(ordenTrabajo);
        ordenVoucher.setVoucher(voucher);

        return ordenVoucherRepository.save(ordenVoucher);
    }

    public OrdenVoucher buscarPorId(Long id) {
        return ordenVoucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenVoucher con ID " + id + " no encontrada"));
    }
}
