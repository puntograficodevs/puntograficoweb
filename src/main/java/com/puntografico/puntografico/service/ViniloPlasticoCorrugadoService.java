package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.MedidaViniloPlasticoCorrugado;
import com.puntografico.puntografico.domain.ViniloPlasticoCorrugado;
import com.puntografico.puntografico.dto.ViniloPlasticoCorrugadoDTO;
import com.puntografico.puntografico.repository.MedidaViniloPlasticoCorrugadoRepository;
import com.puntografico.puntografico.repository.ViniloPlasticoCorrugadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class ViniloPlasticoCorrugadoService {

    private final ViniloPlasticoCorrugadoRepository viniloPlasticoCorrugadoRepository;

    private final OpcionesViniloPlasticoCorrugadoService opcionesViniloPlasticoCorrugadoService;

    public ViniloPlasticoCorrugado guardar(ViniloPlasticoCorrugadoDTO viniloPlasticoCorrugadoDTO, Long idViniloPlasticoCorrugado) {
        validarViniloPlasticoCorrugadoDTO(viniloPlasticoCorrugadoDTO);

        MedidaViniloPlasticoCorrugado medidaViniloPlasticoCorrugado = opcionesViniloPlasticoCorrugadoService.buscarMedidaViniloPlasticoCorrugadoPorId(viniloPlasticoCorrugadoDTO.getMedidaViniloPlasticoCorrugadoId());

        ViniloPlasticoCorrugado viniloPlasticoCorrugado = (idViniloPlasticoCorrugado != null) ? viniloPlasticoCorrugadoRepository.findById(idViniloPlasticoCorrugado).get() : new ViniloPlasticoCorrugado();
        boolean adicionalDisenio = (idViniloPlasticoCorrugado != null) ? viniloPlasticoCorrugado.isConAdicionalDisenio() : viniloPlasticoCorrugadoDTO.getConAdicionalDisenio();
        boolean conOjales = (idViniloPlasticoCorrugado != null) ? viniloPlasticoCorrugado.isConOjales() : viniloPlasticoCorrugadoDTO.getConOjales();

        viniloPlasticoCorrugado.setMedidaPersonalizada(viniloPlasticoCorrugadoDTO.getMedidaPersonalizada());
        viniloPlasticoCorrugado.setConOjales(conOjales);
        viniloPlasticoCorrugado.setEnlaceArchivo(viniloPlasticoCorrugadoDTO.getEnlaceArchivo());
        viniloPlasticoCorrugado.setConAdicionalDisenio(adicionalDisenio);
        viniloPlasticoCorrugado.setInformacionAdicional(viniloPlasticoCorrugadoDTO.getInformacionAdicional());
        viniloPlasticoCorrugado.setMedidaViniloPlasticoCorrugado(medidaViniloPlasticoCorrugado);
        viniloPlasticoCorrugado.setCantidad(viniloPlasticoCorrugadoDTO.getCantidad());

        return viniloPlasticoCorrugadoRepository.save(viniloPlasticoCorrugado);
    }

    private void validarViniloPlasticoCorrugadoDTO(ViniloPlasticoCorrugadoDTO viniloPlasticoCorrugadoDTO) {
        Assert.notNull(viniloPlasticoCorrugadoDTO.getMedidaViniloPlasticoCorrugadoId(), "medidaViniloPlasticoCorrugadoString es un dato obligatorio.");
        Assert.notNull(viniloPlasticoCorrugadoDTO.getCantidad(), "cantidadString es un dato obligatorio.");
    }
}
