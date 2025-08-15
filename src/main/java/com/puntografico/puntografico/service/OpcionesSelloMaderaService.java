package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.TamanioSelloMadera;
import com.puntografico.puntografico.repository.TamanioSelloMaderaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesSelloMaderaService {

    private final TamanioSelloMaderaRepository tamanioSelloMaderaRepository;

    public OpcionesSelloMaderaService(TamanioSelloMaderaRepository tamanioSelloMaderaRepository) {
        this.tamanioSelloMaderaRepository = tamanioSelloMaderaRepository;
    }

    public List<TamanioSelloMadera> buscarTodosTamanioSelloMadera() {
        return tamanioSelloMaderaRepository.findAll();
    }
}
