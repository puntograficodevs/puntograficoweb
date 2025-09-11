package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional @AllArgsConstructor
public class OpcionesFolletoService {

    private final TipoPapelFolletoRepository tipoPapelFolletoRepository;
    private final TipoColorFolletoRepository tipoColorFolletoRepository;
    private final TipoFazFolletoRepository tipoFazFolletoRepository;
    private final TamanioHojaFolletoRepository tamanioHojaFolletoRepository;
    private final TipoFolletoRepository tipoFolletoRepository;
    private final CantidadFolletoRepository cantidadFolletoRepository;

    public List<TipoPapelFolleto> buscarTodosTipoPapelFolleto() {
        return tipoPapelFolletoRepository.findAll();
    }

    public List<TipoColorFolleto> buscarTodosTipoColorFolleto() {
        return tipoColorFolletoRepository.findAll();
    }

    public List<TipoFazFolleto> buscarTodosTipoFazFolleto() {
        return tipoFazFolletoRepository.findAll();
    }

    public List<TamanioHojaFolleto> buscarTodosTamanioHojaFolleto() {
        return tamanioHojaFolletoRepository.findAll();
    }

    public List<TipoFolleto> buscarTodosTipoFolleto() {
        return tipoFolletoRepository.findAll();
    }

    public List<CantidadFolleto> buscarTodosCantidadFolleto() {
        return cantidadFolletoRepository.findAll();
    }
}
