package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesFolletoService {

    private final TipoPapelFolletoRepository tipoPapelFolletoRepository;
    private final TipoColorFolletoRepository tipoColorFolletoRepository;
    private final TipoFazFolletoRepository tipoFazFolletoRepository;
    private final TamanioHojaFolletoRepository tamanioHojaFolletoRepository;
    private final TipoFolletoRepository tipoFolletoRepository;
    private final CantidadFolletoRepository cantidadFolletoRepository;

    public OpcionesFolletoService(TipoPapelFolletoRepository tipoPapelFolletoRepository, TipoColorFolletoRepository tipoColorFolletoRepository, TipoFazFolletoRepository tipoFazFolletoRepository, TamanioHojaFolletoRepository tamanioHojaFolletoRepository, TipoFolletoRepository tipoFolletoRepository, CantidadFolletoRepository cantidadFolletoRepository) {
        this.tipoPapelFolletoRepository = tipoPapelFolletoRepository;
        this.tipoColorFolletoRepository = tipoColorFolletoRepository;
        this.tipoFazFolletoRepository = tipoFazFolletoRepository;
        this.tamanioHojaFolletoRepository = tamanioHojaFolletoRepository;
        this.tipoFolletoRepository = tipoFolletoRepository;
        this.cantidadFolletoRepository = cantidadFolletoRepository;
    }

    List<TipoPapelFolleto> buscarTodosTipoPapelFolleto() {
        return tipoPapelFolletoRepository.findAll();
    }

    List<TipoColorFolleto> buscarTodosTipoColorFolleto() {
        return tipoColorFolletoRepository.findAll();
    }

    List<TipoFazFolleto> buscarTodosTipoFazFolleto() {
        return tipoFazFolletoRepository.findAll();
    }

    List<TamanioHojaFolleto> buscarTodosTamanioHojaFolleto() {
        return tamanioHojaFolletoRepository.findAll();
    }

    List<TipoFolleto> buscarTodosTipoFolleto() {
        return tipoFolletoRepository.findAll();
    }

    List<CantidadFolleto> buscarTodosCantidadFolleto() {
        return cantidadFolletoRepository.findAll();
    }
}
