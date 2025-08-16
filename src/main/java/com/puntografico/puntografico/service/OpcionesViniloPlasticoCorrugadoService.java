package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.MedidaViniloPlasticoCorrugado;
import com.puntografico.puntografico.repository.MedidaViniloPlasticoCorrugadoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesViniloPlasticoCorrugadoService {

    private final MedidaViniloPlasticoCorrugadoRepository medidaViniloPlasticoCorrugadoRepository;

    public OpcionesViniloPlasticoCorrugadoService(MedidaViniloPlasticoCorrugadoRepository medidaViniloPlasticoCorrugadoRepository) {
        this.medidaViniloPlasticoCorrugadoRepository = medidaViniloPlasticoCorrugadoRepository;
    }

    public List<MedidaViniloPlasticoCorrugado> buscarTodosMedidaViniloPlasticoCorrugado() {
        return medidaViniloPlasticoCorrugadoRepository.findAll();
    }
}
