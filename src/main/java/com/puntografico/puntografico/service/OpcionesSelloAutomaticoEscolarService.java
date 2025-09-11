package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.ModeloSelloAutomaticoEscolar;
import com.puntografico.puntografico.repository.ModeloSelloAutomaticoEscolarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional @AllArgsConstructor
public class OpcionesSelloAutomaticoEscolarService {

    private final ModeloSelloAutomaticoEscolarRepository modeloSelloAutomaticoEscolarRepository;

    public List<ModeloSelloAutomaticoEscolar> buscarTodosModeloSelloAutomaticoEscolar() {
        return modeloSelloAutomaticoEscolarRepository.findAll();
    }
}
