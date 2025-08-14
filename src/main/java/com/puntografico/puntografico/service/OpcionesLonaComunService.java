package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.MedidaLonaComun;
import com.puntografico.puntografico.domain.TipoLonaComun;
import com.puntografico.puntografico.repository.MedidaLonaComunRepository;
import com.puntografico.puntografico.repository.TipoLonaComunRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesLonaComunService {

    private final TipoLonaComunRepository tipoLonaComunRepository;
    private final MedidaLonaComunRepository medidaLonaComunRepository;

    public OpcionesLonaComunService(TipoLonaComunRepository tipoLonaComunRepository, MedidaLonaComunRepository medidaLonaComunRepository) {
        this.tipoLonaComunRepository = tipoLonaComunRepository;
        this.medidaLonaComunRepository = medidaLonaComunRepository;
    }

    public List<TipoLonaComun> buscarTodosTipoLonaComun() {
        return tipoLonaComunRepository.findAll();
    }

    public List<MedidaLonaComun> buscarTodosMedidaLonaComun() {
        return medidaLonaComunRepository.findAll();
    }
}
