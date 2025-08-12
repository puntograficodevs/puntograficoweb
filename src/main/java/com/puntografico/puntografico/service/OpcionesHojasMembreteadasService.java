package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadHojasMembreteadas;
import com.puntografico.puntografico.domain.MedidaHojasMembreteadas;
import com.puntografico.puntografico.domain.TipoColorHojasMembreteadas;
import com.puntografico.puntografico.repository.CantidadHojasMembreteadasRepository;
import com.puntografico.puntografico.repository.MedidaHojasMembreteadasRepository;
import com.puntografico.puntografico.repository.TipoColorHojasMembreteadasRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesHojasMembreteadasService {

    private final MedidaHojasMembreteadasRepository medidaHojasMembreteadasRepository;
    private final TipoColorHojasMembreteadasRepository tipoColorHojasMembreteadasRepository;
    private final CantidadHojasMembreteadasRepository cantidadHojasMembreteadasRepository;

    public OpcionesHojasMembreteadasService(MedidaHojasMembreteadasRepository medidaHojasMembreteadasRepository, TipoColorHojasMembreteadasRepository tipoColorHojasMembreteadasRepository, CantidadHojasMembreteadasRepository cantidadHojasMembreteadasRepository) {
        this.medidaHojasMembreteadasRepository = medidaHojasMembreteadasRepository;
        this.tipoColorHojasMembreteadasRepository = tipoColorHojasMembreteadasRepository;
        this.cantidadHojasMembreteadasRepository = cantidadHojasMembreteadasRepository;
    }

    public List<MedidaHojasMembreteadas> buscarTodosMedidaHojasMembreteadas() {
        return medidaHojasMembreteadasRepository.findAll();
    }

    public List<TipoColorHojasMembreteadas> buscarTodosTipoColorHojasMembreteadas() {
        return tipoColorHojasMembreteadasRepository.findAll();
    }

    public List<CantidadHojasMembreteadas> buscarTodosCantidadHojasMembreteadas() {
        return cantidadHojasMembreteadasRepository.findAll();
    }
}
