package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.dto.EtiquetaDTO;
import com.puntografico.puntografico.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;

    private final OpcionesEtiquetaService opcionesEtiquetaService;

    public Etiqueta guardar(EtiquetaDTO etiquetaDTO, Long idEtiqueta) {
        validarEtiquetaDTO(etiquetaDTO);

        TipoPapelEtiqueta tipoPapelEtiqueta = opcionesEtiquetaService.buscarTipoPapelEtiquetaPorId(etiquetaDTO.getTipoPapelEtiquetaId());
        TipoLaminadoEtiqueta tipoLaminadoEtiqueta = opcionesEtiquetaService.buscarTipoLaminadoEtiquetaPorId(etiquetaDTO.getTipoLaminadoEtiquetaId());
        TamanioPerforacion tamanioPerforacion = opcionesEtiquetaService.buscarTamanioPerforacionPorId(etiquetaDTO.getTamanioPerforacionId());
        TipoFazEtiqueta tipoFazEtiqueta = opcionesEtiquetaService.buscarTipoFazEtiquetaPorId(etiquetaDTO.getTipoFazEtiquetaId());
        CantidadEtiqueta cantidadEtiqueta = opcionesEtiquetaService.buscarCantidadEtiquetaPorId(etiquetaDTO.getCantidadEtiquetaId());
        MedidaEtiqueta medidaEtiqueta = opcionesEtiquetaService.buscarMedidaEtiquetaPorId(etiquetaDTO.getMedidaEtiquetaId());
        Integer cantidad = etiquetaDTO.getCantidad();

        if (cantidad == null) {
            cantidad = Integer.valueOf(cantidadEtiqueta.getCantidad());
        }

        Etiqueta etiqueta = (idEtiqueta != null) ? etiquetaRepository.findById(idEtiqueta).get() : new Etiqueta();
        boolean conPerforacionAdicional = (idEtiqueta != null) ? etiqueta.isConPerforacionAdicional() : etiquetaDTO.getConPerforacionAdicional();
        boolean conMarcaAdicional = (idEtiqueta != null) ? etiqueta.isConMarcaAdicional() : etiquetaDTO.getConMarcaAdicional();
        boolean adicionalDisenio = (idEtiqueta != null) ? etiqueta.isConAdicionalDisenio() : etiquetaDTO.getConAdicionalDisenio();

        etiqueta.setMedidaPersonalizada(etiquetaDTO.getMedidaPersonalizada());
        etiqueta.setConPerforacionAdicional(conPerforacionAdicional);
        etiqueta.setConMarcaAdicional(conMarcaAdicional);
        etiqueta.setEnlaceArchivo(etiquetaDTO.getEnlaceArchivo());
        etiqueta.setConAdicionalDisenio(adicionalDisenio);
        etiqueta.setInformacionAdicional(etiquetaDTO.getInformacionAdicional());
        etiqueta.setTipoPapelEtiqueta(tipoPapelEtiqueta);
        etiqueta.setTipoLaminadoEtiqueta(tipoLaminadoEtiqueta);
        etiqueta.setTamanioPerforacion(tamanioPerforacion);
        etiqueta.setTipoFazEtiqueta(tipoFazEtiqueta);
        etiqueta.setCantidadEtiqueta(cantidadEtiqueta);
        etiqueta.setMedidaEtiqueta(medidaEtiqueta);
        etiqueta.setCantidad(cantidad);

        return etiquetaRepository.save(etiqueta);
    }

    private void validarEtiquetaDTO(EtiquetaDTO etiquetaDTO) {
        Assert.notNull(etiquetaDTO.getTipoPapelEtiquetaId(), "El tipo de papel es un dato obligatorio.");
        Assert.notNull(etiquetaDTO.getTipoLaminadoEtiquetaId(), "El tipo de laminado es un dato obligatorio.");
        Assert.notNull(etiquetaDTO.getTamanioPerforacionId(), "El tamaño de la perforación es un dato obligatorio.");
        Assert.notNull(etiquetaDTO.getTipoFazEtiquetaId(),"El tipo de faz es un dato obligatorio.");
        Assert.notNull(etiquetaDTO.getMedidaEtiquetaId(), "La medida es un dato obligatorio.");
        Assert.notNull(etiquetaDTO.getCantidadEtiquetaId(), "La cantidad es un dato obligatorio.");
    }
}
