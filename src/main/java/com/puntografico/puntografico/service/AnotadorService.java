package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Anotador;
import com.puntografico.puntografico.dto.AnotadorDTO;
import com.puntografico.puntografico.repository.AnotadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnotadorService {

    @Autowired
    private AnotadorRepository anotadorRepository;

    public Anotador guardar(AnotadorDTO anotadorDTO, Long idAnotador) {
        validarAnotadorDTO(anotadorDTO);

        Anotador anotador = (idAnotador != null) ? anotadorRepository.findById(idAnotador).get() : new Anotador();
        anotador.setCantidadHojas(anotadorDTO.getCantidadHojas());
        anotador.setMedida(anotadorDTO.getMedida());
        anotador.setTipoTapa(anotadorDTO.getTipoTapa());
        anotador.setConAdicionalDisenio(anotadorDTO.getConAdicionalDisenio());
        anotador.setEnlaceArchivo(anotadorDTO.getEnlaceArchivo());
        anotador.setInformacionAdicional(anotadorDTO.getInformacionAdicional());
        anotador.setCantidad(anotadorDTO.getCantidad());

        return anotadorRepository.save(anotador);
    }

    private void validarAnotadorDTO(AnotadorDTO anotadorDTO) {
        Assert.notNull(anotadorDTO.getCantidadHojas(), "La cantidad de hojas es un dato obligatorio.");
        Assert.notNull(anotadorDTO.getTipoTapa(), "El tipo de tapa es un dato obligatorio.");
        Assert.notNull(anotadorDTO.getCantidad(), "La cantidad es un dato obligatorio.");
    }
}
