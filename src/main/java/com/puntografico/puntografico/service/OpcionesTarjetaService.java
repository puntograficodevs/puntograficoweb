package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesTarjetaService {

    private final TipoPapelTarjetaRepository tipoPapelTarjetaRepository;
    private final TipoColorTarjetaRepository tipoColorTarjetaRepository;
    private final TipoFazTarjetaRepository tipoFazTarjetaRepository;
    private final TipoLaminadoTarjetaRepository tipoLaminadoTarjetaRepository;
    private final MedidaTarjetaRepository medidaTarjetaRepository;
    private final CantidadTarjetaRepository cantidadTarjetaRepository;

    public OpcionesTarjetaService(TipoPapelTarjetaRepository tipoPapelTarjetaRepository, TipoColorTarjetaRepository tipoColorTarjetaRepository, TipoFazTarjetaRepository tipoFazTarjetaRepository, TipoLaminadoTarjetaRepository tipoLaminadoTarjetaRepository, MedidaTarjetaRepository medidaTarjetaRepository, CantidadTarjetaRepository cantidadTarjetaRepository) {
        this.tipoPapelTarjetaRepository = tipoPapelTarjetaRepository;
        this.tipoColorTarjetaRepository = tipoColorTarjetaRepository;
        this.tipoFazTarjetaRepository = tipoFazTarjetaRepository;
        this.tipoLaminadoTarjetaRepository = tipoLaminadoTarjetaRepository;
        this.medidaTarjetaRepository = medidaTarjetaRepository;
        this.cantidadTarjetaRepository = cantidadTarjetaRepository;
    }

    public List<TipoPapelTarjeta> buscarTodosTipoPapelTarjeta() {
        return tipoPapelTarjetaRepository.findAll();
    }

    public List<TipoColorTarjeta> buscarTodosTipoColorTarjeta() {
        return tipoColorTarjetaRepository.findAll();
    }

    public List<TipoFazTarjeta> buscarTodosTipoFazTarjeta() {
        return tipoFazTarjetaRepository.findAll();
    }

    public List<TipoLaminadoTarjeta> buscarTodosTipoLaminadoTarjeta() {
        return tipoLaminadoTarjetaRepository.findAll();
    }

    public List<MedidaTarjeta> buscarTodosMedidaTarjeta() {
        return medidaTarjetaRepository.findAll();
    }

    public List<CantidadTarjeta> buscarTodosCantidadTarjeta() {
        return cantidadTarjetaRepository.findAll();
    }
}
