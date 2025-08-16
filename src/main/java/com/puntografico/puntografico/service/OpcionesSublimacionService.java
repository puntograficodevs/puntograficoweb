package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadSublimacion;
import com.puntografico.puntografico.domain.MaterialSublimacion;
import com.puntografico.puntografico.repository.CantidadSublimacionRepository;
import com.puntografico.puntografico.repository.MaterialSublimacionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesSublimacionService {

    private final MaterialSublimacionRepository materialSublimacionRepository;
    private final CantidadSublimacionRepository cantidadSublimacionRepository;

    public OpcionesSublimacionService(MaterialSublimacionRepository materialSublimacionRepository, CantidadSublimacionRepository cantidadSublimacionRepository) {
        this.materialSublimacionRepository = materialSublimacionRepository;
        this.cantidadSublimacionRepository = cantidadSublimacionRepository;
    }

    public List<MaterialSublimacion> buscarTodosMaterialSublimacion() {
        return materialSublimacionRepository.findAll();
    }

    public List<CantidadSublimacion> buscarTodosCantidadSublimacion() {
        return cantidadSublimacionRepository.findAll();
    }
}
