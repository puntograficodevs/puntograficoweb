package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.TipoColorOtro;
import com.puntografico.puntografico.repository.TipoColorOtroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional @AllArgsConstructor
public class OpcionesOtroService {

    private final TipoColorOtroRepository tipoColorOtroRepository;

    public List<TipoColorOtro> buscarTodosTipoColorOtro() {
        return tipoColorOtroRepository.findAll();
    }
}
