package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadTurnero;
import com.puntografico.puntografico.domain.MedidaTurnero;
import com.puntografico.puntografico.domain.TipoColorTurnero;
import com.puntografico.puntografico.repository.CantidadTurneroRepository;
import com.puntografico.puntografico.repository.MedidaTurneroRepository;
import com.puntografico.puntografico.repository.TipoColorTurneroRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesTurneroService {

    private final TipoColorTurneroRepository tipoColorTurneroRepository;
    private final CantidadTurneroRepository cantidadTurneroRepository;
    private final MedidaTurneroRepository medidaTurneroRepository;

    public OpcionesTurneroService(TipoColorTurneroRepository tipoColorTurneroRepository, CantidadTurneroRepository cantidadTurneroRepository, MedidaTurneroRepository medidaTurneroRepository) {
        this.tipoColorTurneroRepository = tipoColorTurneroRepository;
        this.cantidadTurneroRepository = cantidadTurneroRepository;
        this.medidaTurneroRepository = medidaTurneroRepository;
    }

    public List<TipoColorTurnero> buscarTodosTipoColorTurnero() {
        return tipoColorTurneroRepository.findAll();
    }

    public List<CantidadTurnero> buscarTodosCantidadTurnero() {
        return cantidadTurneroRepository.findAll();
    }

    public List<MedidaTurnero> buscarTodosMedidaTurnero() {
        return medidaTurneroRepository.findAll();
    }
}
