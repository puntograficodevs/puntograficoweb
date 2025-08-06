package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.TipoFazCarpetaSolapa;
import com.puntografico.puntografico.domain.TipoLaminadoCarpetaSolapa;
import com.puntografico.puntografico.repository.TipoFazCarpetaSolapaRepository;
import com.puntografico.puntografico.repository.TipoLaminadoCarpetaSolapaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OpcionesCarpetaSolapaService {

    private final TipoLaminadoCarpetaSolapaRepository tipoLaminadoCarpetaSolapaRepository;

    private final TipoFazCarpetaSolapaRepository tipoFazCarpetaSolapaRepository;


    public OpcionesCarpetaSolapaService(TipoLaminadoCarpetaSolapaRepository tipoLaminadoCarpetaSolapaRepository, TipoFazCarpetaSolapaRepository tipoFazCarpetaSolapaRepository) {
        this.tipoLaminadoCarpetaSolapaRepository = tipoLaminadoCarpetaSolapaRepository;
        this.tipoFazCarpetaSolapaRepository = tipoFazCarpetaSolapaRepository;
    }

    public List<TipoLaminadoCarpetaSolapa> buscarTodosTipoLaminadoCarpetaSolapa() {
        return tipoLaminadoCarpetaSolapaRepository.findAll();
    }

    public List<TipoFazCarpetaSolapa> buscarTodosTipoFazCarpetaSolapa() {
        return tipoFazCarpetaSolapaRepository.findAll();
    }
}
