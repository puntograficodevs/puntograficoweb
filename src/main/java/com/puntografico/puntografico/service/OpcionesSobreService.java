package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadSobre;
import com.puntografico.puntografico.domain.MedidaSobre;
import com.puntografico.puntografico.domain.TipoColorSobre;
import com.puntografico.puntografico.repository.CantidadSobreRepository;
import com.puntografico.puntografico.repository.MedidaSobreRepository;
import com.puntografico.puntografico.repository.TipoColorSobreRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesSobreService {

    private final MedidaSobreRepository medidaSobreRepository;
    private final TipoColorSobreRepository tipoColorSobreRepository;
    private final CantidadSobreRepository cantidadSobreRepository;

    public OpcionesSobreService(MedidaSobreRepository medidaSobreRepository, TipoColorSobreRepository tipoColorSobreRepository, CantidadSobreRepository cantidadSobreRepository) {
        this.medidaSobreRepository = medidaSobreRepository;
        this.tipoColorSobreRepository = tipoColorSobreRepository;
        this.cantidadSobreRepository = cantidadSobreRepository;
    }

    public List<MedidaSobre> buscarTodosMedidaSobre() {
        return medidaSobreRepository.findAll();
    }

    public List<TipoColorSobre> buscarTodosTipoColorSobre() {
        return tipoColorSobreRepository.findAll();
    }

    public List<CantidadSobre> buscarTodosCantidadSobre() {
        return cantidadSobreRepository.findAll();
    }
}
