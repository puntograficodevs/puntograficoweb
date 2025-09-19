package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadCierraBolsas;
import com.puntografico.puntografico.domain.CierraBolsas;
import com.puntografico.puntografico.domain.MedidaCierraBolsas;
import com.puntografico.puntografico.domain.TipoTroqueladoCierraBolsas;
import com.puntografico.puntografico.dto.CierraBolsasDTO;
import com.puntografico.puntografico.repository.CierraBolsasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class CierraBolsasService {

    private final CierraBolsasRepository cierraBolsasRepository;

    private final OpcionesCierraBolsasService opcionesCierraBolsasService;

    public CierraBolsas guardar(CierraBolsasDTO cierraBolsasDTO, Long idCierraBolsas) {
        validarCierraBolsasDTO(cierraBolsasDTO);

        CierraBolsas cierraBolsas = new CierraBolsas();
        boolean adicionalDisenio = (idCierraBolsas != null) ? cierraBolsas.isConAdicionalDisenio() : cierraBolsasDTO.getConAdicionalDisenio();
        TipoTroqueladoCierraBolsas tipoTroqueladoCierraBolsas = opcionesCierraBolsasService.buscarTipoTroqueladoCierraBolsasPorId(cierraBolsasDTO.getTipoTroqueladoCierraBolsasId());
        MedidaCierraBolsas medidaCierraBolsas = opcionesCierraBolsasService.buscarMedidaCierraBolsasPorId(cierraBolsasDTO.getMedidaCierraBolsasId());
        CantidadCierraBolsas cantidadCierraBolsas = opcionesCierraBolsasService.buscarCantidadCierraBolsasPorId(cierraBolsasDTO.getCantidadCierraBolsasId());
        Integer cantidad = cierraBolsasDTO.getCantidad();

        if (cantidad == null) {
            cantidad = Integer.valueOf(cantidadCierraBolsas.getCantidad());
        }

        cierraBolsas.setTipoTroqueladoCierraBolsas(tipoTroqueladoCierraBolsas);
        cierraBolsas.setMedidaCierraBolsas(medidaCierraBolsas);
        cierraBolsas.setMedidaPersonalizada(cierraBolsasDTO.getMedidaPersonalizada());
        cierraBolsas.setCantidadCierraBolsas(cantidadCierraBolsas);
        cierraBolsas.setCantidad(cantidad);
        cierraBolsas.setEnlaceArchivo(cierraBolsasDTO.getEnlaceArchivo());
        cierraBolsas.setConAdicionalDisenio(adicionalDisenio);
        cierraBolsas.setInformacionAdicional(cierraBolsasDTO.getInformacionAdicional());

        return cierraBolsasRepository.save(cierraBolsas);
    }

    private void validarCierraBolsasDTO(CierraBolsasDTO cierraBolsasDTO) {
        Assert.notNull(cierraBolsasDTO.getTipoTroqueladoCierraBolsasId(), "El tipo de troquelado es un dato obligatorio.");
        Assert.notNull(cierraBolsasDTO.getMedidaCierraBolsasId(), "La medida es un dato obligatorio.");
        Assert.notNull(cierraBolsasDTO.getCantidadCierraBolsasId(), "La opción de cantidad es un dato obligatorio.");
    }
}
