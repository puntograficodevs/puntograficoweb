package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.SelloMadera;
import com.puntografico.puntografico.domain.TamanioSelloMadera;
import com.puntografico.puntografico.dto.SelloMaderaDTO;
import com.puntografico.puntografico.repository.SelloMaderaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class SelloMaderaService {

    private final SelloMaderaRepository selloMaderaRepository;

    private final OpcionesSelloMaderaService opcionesSelloMaderaService;

    public SelloMadera guardar(SelloMaderaDTO selloMaderaDTO, Long idSelloMadera) {
        validarSelloMaderaDTO(selloMaderaDTO);

        TamanioSelloMadera tamanioSelloMadera = opcionesSelloMaderaService.buscarTamanioSelloMaderaPorId(selloMaderaDTO.getTamanioSelloMaderaId());

        SelloMadera selloMadera = (idSelloMadera != null) ? selloMaderaRepository.findById(idSelloMadera).get() : new SelloMadera();
        boolean adicionalPerilla = (idSelloMadera != null) ? selloMadera.isConAdicionalPerilla() : selloMaderaDTO.getConAdicionalPerilla();
        boolean adicionalDisenio = (idSelloMadera != null) ? selloMadera.isConAdicionalDisenio() : selloMaderaDTO.getConAdicionalDisenio();

        selloMadera.setTamanioPersonalizado(selloMaderaDTO.getTamanioPersonalizado());
        selloMadera.setConAdicionalPerilla(adicionalPerilla);
        selloMadera.setDetalleSello(selloMaderaDTO.getDetalleSello());
        selloMadera.setTipografiaLineaUno(selloMaderaDTO.getTipografiaLineaUno());
        selloMadera.setEnlaceArchivo(selloMaderaDTO.getEnlaceArchivo());
        selloMadera.setConAdicionalDisenio(adicionalDisenio);
        selloMadera.setInformacionAdicional(selloMaderaDTO.getInformacionAdicional());
        selloMadera.setTamanioSelloMadera(tamanioSelloMadera);
        selloMadera.setCantidad(selloMaderaDTO.getCantidad());

        return selloMaderaRepository.save(selloMadera);
    }

    private void validarSelloMaderaDTO(SelloMaderaDTO selloMaderaDTO) {
        Assert.notNull(selloMaderaDTO.getTamanioSelloMaderaId(), "El tamaño es un dato obligatorio.");
        Assert.notNull(selloMaderaDTO.getCantidad(), "La cantidad es un dato obligatorio.");
    }
}
