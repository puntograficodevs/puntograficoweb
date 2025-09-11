package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.TipoColorRifa;
import com.puntografico.puntografico.domain.TipoPapelRifa;
import com.puntografico.puntografico.domain.TipoTroqueladoRifa;
import com.puntografico.puntografico.repository.TipoColorRifaRepository;
import com.puntografico.puntografico.repository.TipoPapelRifaRepository;
import com.puntografico.puntografico.repository.TipoTroqueladoRifaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional @AllArgsConstructor
public class OpcionesRifasContribucionService {

    private final TipoPapelRifaRepository tipoPapelRifaRepository;
    private final TipoColorRifaRepository tipoColorRifaRepository;
    private final TipoTroqueladoRifaRepository tipoTroqueladoRifaRepository;

    public List<TipoPapelRifa> buscarTodosTipoPapelRifa() {
        return tipoPapelRifaRepository.findAll();
    }

    public List<TipoTroqueladoRifa> buscarTodosTipoTroqueladoRifa() {
        return tipoTroqueladoRifaRepository.findAll();
    }

    public List<TipoColorRifa> buscarTodosTipoColorRifa() {
        return tipoColorRifaRepository.findAll();
    }
}
