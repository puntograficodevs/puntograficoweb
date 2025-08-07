package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadCierraBolsas;
import com.puntografico.puntografico.domain.MedidaCierraBolsas;
import com.puntografico.puntografico.domain.TipoTroqueladoCierraBolsas;
import com.puntografico.puntografico.repository.CantidadCierraBolsasRepository;
import com.puntografico.puntografico.repository.MedidaCierraBolsasRepository;
import com.puntografico.puntografico.repository.TipoTroqueladoCierraBolsasRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesCierraBolsasService {

    private final TipoTroqueladoCierraBolsasRepository tipoTroqueladoCierraBolsasRepository;
    private final MedidaCierraBolsasRepository medidaCierraBolsasRepository;
    private final CantidadCierraBolsasRepository cantidadCierraBolsasRepository;

    public OpcionesCierraBolsasService(TipoTroqueladoCierraBolsasRepository tipoTroqueladoCierraBolsasRepository, MedidaCierraBolsasRepository medidaCierraBolsasRepository, CantidadCierraBolsasRepository cantidadCierraBolsasRepository) {
        this.tipoTroqueladoCierraBolsasRepository = tipoTroqueladoCierraBolsasRepository;
        this.medidaCierraBolsasRepository = medidaCierraBolsasRepository;
        this.cantidadCierraBolsasRepository = cantidadCierraBolsasRepository;
    }

    public List<TipoTroqueladoCierraBolsas> buscarTodosTipoTroqueladoCierraBolsas() {
        return tipoTroqueladoCierraBolsasRepository.findAll();
    }

    public List<MedidaCierraBolsas> buscarTodosMedidaCierraBolsas() {
        return medidaCierraBolsasRepository.findAll();
    }

    public List<CantidadCierraBolsas> buscarTodosCantidadCierraBolsas() {
        return cantidadCierraBolsasRepository.findAll();
    }
}
