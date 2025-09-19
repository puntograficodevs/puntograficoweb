package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.dto.LonaPublicitariaDTO;
import com.puntografico.puntografico.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class LonaPublicitariaService {

    private final LonaPublicitariaRepository lonaPublicitariaRepository;

    private final OpcionesLonaPublicitariaService opcionesLonaPublicitariaService;

    public LonaPublicitaria guardar(LonaPublicitariaDTO lonaPublicitariaDTO, Long idLonaPublicitaria) {
        validarLonaPublicitariaDTO(lonaPublicitariaDTO);

        MedidaLonaPublicitaria medidaLonaPublicitaria = opcionesLonaPublicitariaService.buscarMedidaLonaPublicitariaPorId(lonaPublicitariaDTO.getMedidaLonaPublicitariaId());
        TipoLonaPublicitaria tipoLonaPublicitaria = opcionesLonaPublicitariaService.buscarTipoLonaPublicitariaPorId(lonaPublicitariaDTO.getTipoLonaPublicitariaId());

        LonaPublicitaria lonaPublicitaria = (idLonaPublicitaria != null) ? lonaPublicitariaRepository.findById(idLonaPublicitaria).get() : new LonaPublicitaria();
        lonaPublicitaria.setMedidaLonaPublicitaria(medidaLonaPublicitaria);
        lonaPublicitaria.setTipoLonaPublicitaria(tipoLonaPublicitaria);
        lonaPublicitaria.setConAdicionalPortabanner(lonaPublicitariaDTO.getConAdicionalPortabanner());
        lonaPublicitaria.setConOjales(lonaPublicitariaDTO.getConOjales());
        lonaPublicitaria.setConOjalesConRefuerzo(lonaPublicitariaDTO.getConOjalesConRefuerzo());
        lonaPublicitaria.setConBolsillos(lonaPublicitariaDTO.getConBolsillos());
        lonaPublicitaria.setConDemasiaParaTensado(lonaPublicitariaDTO.getConDemasiaParaTensado());
        lonaPublicitaria.setConSolapado(lonaPublicitariaDTO.getConSolapado());
        lonaPublicitaria.setConAdicionalDisenio(lonaPublicitariaDTO.getConAdicionalDisenio());
        lonaPublicitaria.setEnlaceArchivo(lonaPublicitariaDTO.getEnlaceArchivo());
        lonaPublicitaria.setInformacionAdicional(lonaPublicitariaDTO.getInformacionAdicional());
        lonaPublicitaria.setCantidad(lonaPublicitariaDTO.getCantidad());

        return lonaPublicitariaRepository.save(lonaPublicitaria);
    }

    private void validarLonaPublicitariaDTO(LonaPublicitariaDTO lonaPublicitariaDTO) {
        Assert.notNull(lonaPublicitariaDTO.getMedidaLonaPublicitariaId(), "La medida es un dato obligatorio.");
        Assert.notNull(lonaPublicitariaDTO.getTipoLonaPublicitariaId(), "El tipo de lona es un dato obligatorio.");
        Assert.notNull(lonaPublicitariaDTO.getCantidad(), "La cantidad es un dato obligatorio.");
    }
}
