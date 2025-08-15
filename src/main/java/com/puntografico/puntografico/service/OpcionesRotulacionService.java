package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.TipoCorteRotulacion;
import com.puntografico.puntografico.domain.TipoRotulacion;
import com.puntografico.puntografico.repository.TipoCorteRotulacionRepository;
import com.puntografico.puntografico.repository.TipoRotulacionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesRotulacionService {

    private final TipoRotulacionRepository tipoRotulacionRepository;
    private final TipoCorteRotulacionRepository tipoCorteRotulacionRepository;

    public OpcionesRotulacionService(TipoRotulacionRepository tipoRotulacionRepository, TipoCorteRotulacionRepository tipoCorteRotulacionRepository) {
        this.tipoRotulacionRepository = tipoRotulacionRepository;
        this.tipoCorteRotulacionRepository = tipoCorteRotulacionRepository;
    }

    public List<TipoRotulacion> buscarTodosTipoRotulacion() {
        return tipoRotulacionRepository.findAll();
    }

    public List<TipoCorteRotulacion> buscarTodosTipoCorteRotulacion() {
        return tipoCorteRotulacionRepository.findAll();
    }
}
