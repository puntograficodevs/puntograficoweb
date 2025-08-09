package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesEntradaService {

    private final TipoPapelEntradaRepository tipoPapelEntradaRepository;
    private final TipoColorEntradaRepository tipoColorEntradaRepository;
    private final TipoTroqueladoEntradaRepository tipoTroqueladoEntradaRepository;
    private final MedidaEntradaRepository medidaEntradaRepository;
    private final CantidadEntradaRepository cantidadEntradaRepository;
    private final NumeradoEntradaRepository numeradoEntradaRepository;
    private final TerminacionEntradaRepository terminacionEntradaRepository;

    public OpcionesEntradaService(TipoPapelEntradaRepository tipoPapelEntradaRepository, TipoColorEntradaRepository tipoColorEntradaRepository, TipoTroqueladoEntradaRepository tipoTroqueladoEntradaRepository, MedidaEntradaRepository medidaEntradaRepository, CantidadEntradaRepository cantidadEntradaRepository, NumeradoEntradaRepository numeradoEntradaRepository, TerminacionEntradaRepository terminacionEntradaRepository) {
        this.tipoPapelEntradaRepository = tipoPapelEntradaRepository;
        this.tipoColorEntradaRepository = tipoColorEntradaRepository;
        this.tipoTroqueladoEntradaRepository = tipoTroqueladoEntradaRepository;
        this.medidaEntradaRepository = medidaEntradaRepository;
        this.cantidadEntradaRepository = cantidadEntradaRepository;
        this.numeradoEntradaRepository = numeradoEntradaRepository;
        this.terminacionEntradaRepository = terminacionEntradaRepository;
    }

    public List<TipoPapelEntrada> buscarTodosTipoPapelEntrada() {
        return tipoPapelEntradaRepository.findAll();
    }

    public List<TipoColorEntrada> buscarTodosTipoColorEntrada() {
        return tipoColorEntradaRepository.findAll();
    }

    public List<TipoTroqueladoEntrada> buscarTodosTipoTroqueladoEntrada() {
        return tipoTroqueladoEntradaRepository.findAll();
    }

    public List<MedidaEntrada> buscarTodosMedidaEntrada() {
        return medidaEntradaRepository.findAll();
    }

    public List<CantidadEntrada> buscarTodosCantidadEntrada() {
        return cantidadEntradaRepository.findAll();
    }

    public List<NumeradoEntrada> buscarTodosNumeradoEntrada() {
        return numeradoEntradaRepository.findAll();
    }

    public List<TerminacionEntrada> buscarTodosTerminacionEntrada() {
        return terminacionEntradaRepository.findAll();
    }
}
