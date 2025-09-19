package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CuadernoAnillado;
import com.puntografico.puntografico.domain.MedidaCuadernoAnillado;
import com.puntografico.puntografico.domain.TipoTapaCuadernoAnillado;
import com.puntografico.puntografico.dto.CuadernoAnilladoDTO;
import com.puntografico.puntografico.repository.CuadernoAnilladoRepository;
import com.puntografico.puntografico.repository.MedidaCuadernoAnilladoRepository;
import com.puntografico.puntografico.repository.TipoTapaCuadernoAnilladoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class CuadernoAnilladoService {

    private final CuadernoAnilladoRepository cuadernoAnilladoRepository;

    private final OpcionesCuadernoAnilladoService opcionesCuadernoAnilladoService;

    public CuadernoAnillado guardar(CuadernoAnilladoDTO cuadernoAnilladoDTO, Long idCuadernoAnillado) {
        validarCuadernoAnilladoDTO(cuadernoAnilladoDTO);

        TipoTapaCuadernoAnillado tipoTapaCuadernoAnillado = opcionesCuadernoAnilladoService.buscarTipoTapaCuadernoAnilladoPorId(cuadernoAnilladoDTO.getTipoTapaCuadernoAnilladoId());
        MedidaCuadernoAnillado medidaCuadernoAnillado = opcionesCuadernoAnilladoService.buscarMedidaCuadernoAnilladoPorId(cuadernoAnilladoDTO.getMedidaCuadernoAnilladoId());

        CuadernoAnillado cuadernoAnillado = (idCuadernoAnillado != null) ? cuadernoAnilladoRepository.findById(idCuadernoAnillado).get() : new CuadernoAnillado();
        boolean adicionalDisenio = (idCuadernoAnillado != null) ? cuadernoAnillado.isConAdicionalDisenio() : cuadernoAnilladoDTO.getConAdicionalDisenio();

        cuadernoAnillado.setMedidaPersonalizada(cuadernoAnilladoDTO.getMedidaPersonalizada());
        cuadernoAnillado.setTipoTapaPersonalizada(cuadernoAnilladoDTO.getTipoTapaPersonalizada());
        cuadernoAnillado.setCantidadHojas(cuadernoAnilladoDTO.getCantidadHojas());
        cuadernoAnillado.setEnlaceArchivo(cuadernoAnilladoDTO.getEnlaceArchivo());
        cuadernoAnillado.setConAdicionalDisenio(adicionalDisenio);
        cuadernoAnillado.setInformacionAdicional(cuadernoAnilladoDTO.getInformacionAdicional());
        cuadernoAnillado.setCantidad(cuadernoAnilladoDTO.getCantidad());
        cuadernoAnillado.setTipoTapaCuadernoAnillado(tipoTapaCuadernoAnillado);
        cuadernoAnillado.setMedidaCuadernoAnillado(medidaCuadernoAnillado);

        return cuadernoAnilladoRepository.save(cuadernoAnillado);
    }

    private void validarCuadernoAnilladoDTO(CuadernoAnilladoDTO cuadernoAnilladoDTO) {
        Assert.notNull(cuadernoAnilladoDTO.getCantidadHojas(), "La cantidad de hojas es un dato obligatorio.");
        Assert.notNull(cuadernoAnilladoDTO.getCantidad(), "La cantidad es un dato obligatorio.");
        Assert.notNull(cuadernoAnilladoDTO.getTipoTapaCuadernoAnilladoId(), "El tipo de tapa es un dato obligatorio.");
        Assert.notNull(cuadernoAnilladoDTO.getMedidaCuadernoAnilladoId(), "La medida del cuaderno es un dato obligatorio.");
    }
}
