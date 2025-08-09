package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesEtiquetaService {

    private final TipoPapelEtiquetaRepository tipoPapelEtiquetaRepository;
    private final TipoLaminadoEtiquetaRepository tipoLaminadoEtiquetaRepository;
    private final TamanioPerforacionRepository tamanioPerforacionRepository;
    private final TipoFazEtiquetaRepository tipoFazEtiquetaRepository;
    private final CantidadEtiquetaRepository cantidadEtiquetaRepository;
    private final MedidaEtiquetaRepository medidaEtiquetaRepository;

    public OpcionesEtiquetaService(TipoPapelEtiquetaRepository tipoPapelEtiquetaRepository, TipoLaminadoEtiquetaRepository tipoLaminadoEtiquetaRepository, TamanioPerforacionRepository tamanioPerforacionRepository, TipoFazEtiquetaRepository tipoFazEtiquetaRepository, CantidadEtiquetaRepository cantidadEtiquetaRepository, MedidaEtiquetaRepository medidaEtiquetaRepository) {
        this.tipoPapelEtiquetaRepository = tipoPapelEtiquetaRepository;
        this.tipoLaminadoEtiquetaRepository = tipoLaminadoEtiquetaRepository;
        this.tamanioPerforacionRepository = tamanioPerforacionRepository;
        this.tipoFazEtiquetaRepository = tipoFazEtiquetaRepository;
        this.cantidadEtiquetaRepository = cantidadEtiquetaRepository;
        this.medidaEtiquetaRepository = medidaEtiquetaRepository;
    }

    public List<TipoPapelEtiqueta> buscarTodosTipoPapelEtiqueta() {
        return tipoPapelEtiquetaRepository.findAll();
    }

    public List<TipoLaminadoEtiqueta> buscarTodosTipoLaminadoEtiqueta() {
        return tipoLaminadoEtiquetaRepository.findAll();
    }

    public List<TamanioPerforacion> buscarTodosTamanioPerforacion() {
        return tamanioPerforacionRepository.findAll();
    }

    public List<TipoFazEtiqueta> buscarTodosTipoFazEtiqueta() {
        return tipoFazEtiquetaRepository.findAll();
    }

    public List<CantidadEtiqueta> buscarTodosCantidadEtiqueta() {
        return cantidadEtiquetaRepository.findAll();
    }

    public List<MedidaEtiqueta> buscarTodosMedidaEtiqueta() {
        return medidaEtiquetaRepository.findAll();
    }
}
