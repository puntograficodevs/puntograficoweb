package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.OrdenVoucherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class OrdenVoucherService {

    private final OrdenVoucherRepository ordenVoucherRepository;

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

    public OrdenVoucher buscarPorOrdenId(Long id) {
        return ordenVoucherRepository.findByOrdenTrabajo_Id(id);
    }
}
