package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesTalonarioService {

    private final TipoTalonarioRepository tipoTalonarioRepository;
    private final TipoTroqueladoTalonarioRepository tipoTroqueladoTalonarioRepository;
    private final ModoTalonarioRepository modoTalonarioRepository;
    private final TipoColorTalonarioRepository tipoColorTalonarioRepository;
    private final MedidaTalonarioRepository medidaTalonarioRepository;
    private final TipoPapelTalonarioRepository tipoPapelTalonarioRepository;
    private final CantidadTalonarioRepository cantidadTalonarioRepository;

    public OpcionesTalonarioService(TipoTalonarioRepository tipoTalonarioRepository, TipoTroqueladoTalonarioRepository tipoTroqueladoTalonarioRepository, ModoTalonarioRepository modoTalonarioRepository, TipoColorTalonarioRepository tipoColorTalonarioRepository, MedidaTalonarioRepository medidaTalonarioRepository, TipoPapelTalonarioRepository tipoPapelTalonarioRepository, CantidadTalonarioRepository cantidadTalonarioRepository) {
        this.tipoTalonarioRepository = tipoTalonarioRepository;
        this.tipoTroqueladoTalonarioRepository = tipoTroqueladoTalonarioRepository;
        this.modoTalonarioRepository = modoTalonarioRepository;
        this.tipoColorTalonarioRepository = tipoColorTalonarioRepository;
        this.medidaTalonarioRepository = medidaTalonarioRepository;
        this.tipoPapelTalonarioRepository = tipoPapelTalonarioRepository;
        this.cantidadTalonarioRepository = cantidadTalonarioRepository;
    }

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
