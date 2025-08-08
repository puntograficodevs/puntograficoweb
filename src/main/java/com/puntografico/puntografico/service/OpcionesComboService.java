package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.TipoCombo;
import com.puntografico.puntografico.repository.TipoComboRepository;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesComboService {

    private final TipoComboRepository tipoComboRepository;

    public OpcionesComboService(TipoComboRepository tipoComboRepository) {
        this.tipoComboRepository = tipoComboRepository;
    }

    public List<TipoCombo> buscarTodosTipoCombo() {
        return tipoComboRepository.findAll();
    }
}
