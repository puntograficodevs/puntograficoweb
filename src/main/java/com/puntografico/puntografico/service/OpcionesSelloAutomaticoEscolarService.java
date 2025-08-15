package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.ModeloSelloAutomaticoEscolar;
import com.puntografico.puntografico.repository.ModeloSelloAutomaticoEscolarRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OpcionesSelloAutomaticoEscolarService {

    private final ModeloSelloAutomaticoEscolarRepository modeloSelloAutomaticoEscolarRepository;

    public OpcionesSelloAutomaticoEscolarService(ModeloSelloAutomaticoEscolarRepository modeloSelloAutomaticoEscolarRepository) {
        this.modeloSelloAutomaticoEscolarRepository = modeloSelloAutomaticoEscolarRepository;
    }

    public List<ModeloSelloAutomaticoEscolar> buscarTodosModeloSelloAutomaticoEscolar() {
        return modeloSelloAutomaticoEscolarRepository.findAll();
    }
}
