package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.LonaComun;
import com.puntografico.puntografico.domain.MedidaLonaComun;
import com.puntografico.puntografico.domain.TipoLonaComun;
import com.puntografico.puntografico.dto.LonaComunDTO;
import com.puntografico.puntografico.repository.LonaComunRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class LonaComunService {

    private final LonaComunRepository lonaComunRepository;

    private final OpcionesLonaComunService opcionesLonaComunService;

    public LonaComun guardar(LonaComunDTO lonaComunDTO, Long idLonaComun) {
        validarLonaComunDTO(lonaComunDTO);

        MedidaLonaComun medidaLonaComun = opcionesLonaComunService.buscarMedidaLonaComunPorId(lonaComunDTO.getMedidaLonaComunId());
        TipoLonaComun tipoLonaComun = opcionesLonaComunService.buscarTipoLonaComunPorId(lonaComunDTO.getTipoLonaComunId());

        LonaComun lonaComun = (idLonaComun != null) ? lonaComunRepository.findById(idLonaComun).get() :  new LonaComun();
        lonaComun.setMedidaLonaComun(medidaLonaComun);
        lonaComun.setTipoLonaComun(tipoLonaComun);
        lonaComun.setMedidaPersonalizada(lonaComunDTO.getMedidaPersonalizada());
        lonaComun.setConOjales(lonaComunDTO.getConOjales());
        lonaComun.setConOjalesConRefuerzo(lonaComunDTO.getConOjalesConRefuerzo());
        lonaComun.setConBolsillos(lonaComunDTO.getConBolsillos());
        lonaComun.setConDemasiaParaTensado(lonaComunDTO.getConDemasiaParaTensado());
        lonaComun.setConSolapado(lonaComunDTO.getConSolapado());
        lonaComun.setConAdicionalDisenio(lonaComunDTO.getConAdicionalDisenio());
        lonaComun.setEnlaceArchivo(lonaComunDTO.getEnlaceArchivo());
        lonaComun.setInformacionAdicional(lonaComunDTO.getInformacionAdicional());
        lonaComun.setCantidad(lonaComunDTO.getCantidad());

        return lonaComunRepository.save(lonaComun);
    }

    private void validarLonaComunDTO(LonaComunDTO lonaComunDTO) {
        Assert.notNull(lonaComunDTO.getMedidaLonaComunId(), "La medida es un dato obligatorio.");
        Assert.notNull(lonaComunDTO.getTipoLonaComunId(), "El tipo de lona es un dato obligatorio.");
        Assert.notNull(lonaComunDTO.getCantidad(), "La cantidad es un dato obligatorio.");
    }
}
