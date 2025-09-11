package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.TamanioSelloMadera;
import com.puntografico.puntografico.repository.TamanioSelloMaderaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional @AllArgsConstructor
public class OpcionesSelloMaderaService {

    private final TamanioSelloMaderaRepository tamanioSelloMaderaRepository;

    public List<TamanioSelloMadera> buscarTodosTamanioSelloMadera() {
        return tamanioSelloMaderaRepository.findAll();
    }
}
