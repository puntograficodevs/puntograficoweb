package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.TraeMaterialVinilo;
import com.puntografico.puntografico.repository.TraeMaterialViniloRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesViniloDeCorteService {

    private final TraeMaterialViniloRepository traeMaterialViniloRepository;

    public OpcionesViniloDeCorteService(TraeMaterialViniloRepository traeMaterialViniloRepository) {
        this.traeMaterialViniloRepository = traeMaterialViniloRepository;
    }

    public List<TraeMaterialVinilo>  buscarTodosTraeMaterialVinilo() {
        return traeMaterialViniloRepository.findAll();
    }
}
