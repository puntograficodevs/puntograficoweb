package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.TipoFazCatalogo;
import com.puntografico.puntografico.domain.TipoLaminadoCatalogo;
import com.puntografico.puntografico.repository.TipoFazCatalogoRepository;
import com.puntografico.puntografico.repository.TipoLaminadoCatalogoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesCatalogoService {

    private final TipoFazCatalogoRepository tipoFazCatalogoRepository;

    private final TipoLaminadoCatalogoRepository tipoLaminadoCatalogoRepository;

    public OpcionesCatalogoService(TipoFazCatalogoRepository tipoFazCatalogoRepository, TipoLaminadoCatalogoRepository tipoLaminadoCatalogoRepository) {
        this.tipoFazCatalogoRepository = tipoFazCatalogoRepository;
        this.tipoLaminadoCatalogoRepository = tipoLaminadoCatalogoRepository;
    }

    public List<TipoFazCatalogo> buscarTodosTipoFazCatalogo() {
        return tipoFazCatalogoRepository.findAll();
    }

    public List<TipoLaminadoCatalogo> buscarTodosTipoLaminadoCatalogo() {
        return tipoLaminadoCatalogoRepository.findAll();
    }
}
