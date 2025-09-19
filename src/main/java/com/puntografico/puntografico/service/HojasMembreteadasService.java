package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadHojasMembreteadas;
import com.puntografico.puntografico.domain.HojasMembreteadas;
import com.puntografico.puntografico.domain.MedidaHojasMembreteadas;
import com.puntografico.puntografico.domain.TipoColorHojasMembreteadas;
import com.puntografico.puntografico.dto.HojasMembreteadasDTO;
import com.puntografico.puntografico.repository.CantidadHojasMembreteadasRepository;
import com.puntografico.puntografico.repository.HojasMembreteadasRepository;
import com.puntografico.puntografico.repository.MedidaHojasMembreteadasRepository;
import com.puntografico.puntografico.repository.TipoColorHojasMembreteadasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class HojasMembreteadasService {

    private final OpcionesHojasMembreteadasService opcionesHojasMembreteadasService;

    private final HojasMembreteadasRepository hojasMembreteadasRepository;

    public HojasMembreteadas guardar(HojasMembreteadasDTO hojasMembreteadasDTO, Long idHojasMembreteadas) {
        validarHojasMembreteadasDTO(hojasMembreteadasDTO);

        MedidaHojasMembreteadas medidaHojasMembreteadas = opcionesHojasMembreteadasService.buscarMedidaHojasMembreteadasPorId(hojasMembreteadasDTO.getMedidaHojasMembreteadasId());
        TipoColorHojasMembreteadas tipoColorHojasMembreteadas = opcionesHojasMembreteadasService.buscarTipoColorHojasMembreteadasPorId(hojasMembreteadasDTO.getTipoColorHojasMembreteadasId());
        CantidadHojasMembreteadas cantidadHojasMembreteadas = opcionesHojasMembreteadasService.buscarCantidadHojasMembreteadasPorId(hojasMembreteadasDTO.getCantidadHojasMembreteadasId());
        Integer cantidad = hojasMembreteadasDTO.getCantidad();

        if (cantidad == null) {
            cantidad = Integer.valueOf(cantidadHojasMembreteadas.getCantidad());
        }

        HojasMembreteadas hojasMembreteadas = (idHojasMembreteadas != null) ? hojasMembreteadasRepository.findById(idHojasMembreteadas).get() : new HojasMembreteadas();
        hojasMembreteadas.setMedidaPersonalizada(hojasMembreteadasDTO.getMedidaPersonalizada());
        hojasMembreteadas.setCantidadHojas(hojasMembreteadasDTO.getCantidadHojas());
        hojasMembreteadas.setEnlaceArchivo(hojasMembreteadasDTO.getEnlaceArchivo());
        hojasMembreteadas.setConAdicionalDisenio(hojasMembreteadasDTO.getConAdicionalDisenio());
        hojasMembreteadas.setInformacionAdicional(hojasMembreteadasDTO.getInformacionAdicional());
        hojasMembreteadas.setMedidaHojasMembreteadas(medidaHojasMembreteadas);
        hojasMembreteadas.setTipoColorHojasMembreteadas(tipoColorHojasMembreteadas);
        hojasMembreteadas.setCantidadHojasMembreteadas(cantidadHojasMembreteadas);
        hojasMembreteadas.setCantidad(cantidad);

        return hojasMembreteadasRepository.save(hojasMembreteadas);
    }

    private void validarHojasMembreteadasDTO(HojasMembreteadasDTO hojasMembreteadasDTO) {
        Assert.notNull(hojasMembreteadasDTO.getMedidaHojasMembreteadasId(), "medidaHojasMembreteadasString es un dato obligatorio.");
        Assert.notNull(hojasMembreteadasDTO.getTipoColorHojasMembreteadasId(), "tipoColorHojasMembreteadasString es un dato obligatorio.");
        Assert.notNull(hojasMembreteadasDTO.getCantidadHojasMembreteadasId(), "cantidadHojasMembreteadasString es un dato obligatorio.");
        Assert.notNull(hojasMembreteadasDTO.getCantidadHojas(), "cantidadHojas es un dato obligatorio.");
    }
}
