package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional @AllArgsConstructor
public class OpcionesTalonarioService {

    private final TipoTalonarioRepository tipoTalonarioRepository;
    private final TipoTroqueladoTalonarioRepository tipoTroqueladoTalonarioRepository;
    private final ModoTalonarioRepository modoTalonarioRepository;
    private final TipoColorTalonarioRepository tipoColorTalonarioRepository;
    private final MedidaTalonarioRepository medidaTalonarioRepository;
    private final TipoPapelTalonarioRepository tipoPapelTalonarioRepository;
    private final CantidadTalonarioRepository cantidadTalonarioRepository;

    public List<TipoTalonario> buscarTodosTipoTalonario() {
        return tipoTalonarioRepository.findAll();
    }

    public List<TipoTroqueladoTalonario> buscarTodosTipoTroqueladoTalonario() {
        return tipoTroqueladoTalonarioRepository.findAll();
    }

    public List<ModoTalonario> buscarTodosModoTalonario() {
        return modoTalonarioRepository.findAll();
    }

    public List<TipoColorTalonario> buscarTodosTipoColorTalonario() {
        return tipoColorTalonarioRepository.findAll();
    }

    public List<MedidaTalonario> buscarTodosMedidaTalonario() {
        return medidaTalonarioRepository.findAll();
    }

    public List<TipoPapelTalonario> buscarTodosTipoPapelTalonario() {
        return tipoPapelTalonarioRepository.findAll();
    }

    public List<CantidadTalonario> buscarTodosCantidadTalonario() {
        return cantidadTalonarioRepository.findAll();
    }
}
