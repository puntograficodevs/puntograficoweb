package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.TipoColorAgenda;
import com.puntografico.puntografico.domain.TipoTapaAgenda;
import com.puntografico.puntografico.repository.TipoColorAgendaRepository;
import com.puntografico.puntografico.repository.TipoTapaAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OpcionesAgendaService {

    private final TipoTapaAgendaRepository tipoTapaAgendaRepository;

    private final TipoColorAgendaRepository tipoColorAgendaRepository;

    public OpcionesAgendaService(TipoTapaAgendaRepository tipoTapaAgendaRepository,
                                 TipoColorAgendaRepository tipoColorAgendaRepository) {
        this.tipoTapaAgendaRepository = tipoTapaAgendaRepository;
        this.tipoColorAgendaRepository = tipoColorAgendaRepository;
    }

    public List<TipoTapaAgenda> buscarTodosTipoTapaAgenda() {
        return tipoTapaAgendaRepository.findAll();
    }

    public List<TipoColorAgenda> buscarTodosTipoColorAgenda() {
        return tipoColorAgendaRepository.findAll();
    }
}
