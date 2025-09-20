package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.dto.VoucherDTO;
import com.puntografico.puntografico.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class VoucherService {

    private final VoucherRepository voucherRepository;
    private final OpcionesVoucherService opcionesVoucherService;

    public Voucher guardar(VoucherDTO voucherDTO, Long idVoucher) {
        validarVoucherDTO(voucherDTO);

        MedidaVoucher medidaVoucher = opcionesVoucherService.buscarMedidaVoucherPorId(voucherDTO.getMedidaVoucherId());
        TipoPapelVoucher tipoPapelVoucher = opcionesVoucherService.buscarTipoPapelVoucherPorId(voucherDTO.getTipoPapelVoucherId());
        TipoFazVoucher tipoFazVoucher = opcionesVoucherService.buscarTipoFazVoucherPorId(voucherDTO.getTipoFazVoucherId());
        CantidadVoucher cantidadVoucher = opcionesVoucherService.buscarCantidadVoucherPorId(voucherDTO.getCantidadVoucherId());
        Integer cantidad = voucherDTO.getCantidad();

        if (cantidad == null) {
            cantidad = Integer.valueOf(cantidadVoucher.getCantidad());
        }

        Voucher voucher = (idVoucher != null) ? voucherRepository.findById(idVoucher).get() : new Voucher();
        boolean adicionalDisenio = (idVoucher != null) ? voucher.isConAdicionalDisenio() : voucherDTO.getConAdicionalDisenio();

        voucher.setTipoPapelPersonalizado(voucherDTO.getTipoPapelPersonalizado());
        voucher.setMedidaPersonalizada(voucherDTO.getMedidaPersonalizada());
        voucher.setEnlaceArchivo(voucherDTO.getEnlaceArchivo());
        voucher.setConAdicionalDisenio(adicionalDisenio);
        voucher.setInformacionAdicional(voucherDTO.getInformacionAdicional());
        voucher.setMedidaVoucher(medidaVoucher);
        voucher.setTipoPapelVoucher(tipoPapelVoucher);
        voucher.setTipoFazVoucher(tipoFazVoucher);
        voucher.setCantidadVoucher(cantidadVoucher);
        voucher.setCantidad(cantidad);

        return voucherRepository.save(voucher);
    }

    private void validarVoucherDTO(VoucherDTO voucherDTO) {
        Assert.notNull(voucherDTO.getMedidaVoucherId(), "medidaVoucherString es un dato obligatorio.");
        Assert.notNull(voucherDTO.getTipoPapelVoucherId(), "tipoPapelVoucherString es un dato obligatorio.");
        Assert.notNull(voucherDTO.getTipoFazVoucherId(), "tipoFazVoucherString es un dato obligatorio.");
        Assert.notNull(voucherDTO.getCantidadVoucherId(), "cantidadVoucherString es un dato obligatorio.");
    }
}
