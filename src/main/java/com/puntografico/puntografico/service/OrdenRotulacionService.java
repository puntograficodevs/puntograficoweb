package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.OrdenLonaPublicitariaRepository;
import com.puntografico.puntografico.repository.OrdenRotulacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenRotulacionService {

    @Autowired
    private OrdenRotulacionRepository ordenRotulacionRepository;

    public OrdenRotulacion crear(OrdenTrabajo ordenTrabajo, Rotulacion rotulacion) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(rotulacion, "Debe venir una rotulacion para enlazar.");

        OrdenRotulacion ordenRotulacion = new OrdenRotulacion();
        ordenRotulacion.setRotulacion(rotulacion);
        ordenRotulacion.setOrdenTrabajo(ordenTrabajo);
        ordenRotulacion.setCantidad(rotulacion.getCantidad());

        return ordenRotulacionRepository.save(ordenRotulacion);
    }

    public OrdenRotulacion buscarPorId(Long id) {
        return ordenRotulacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenRotulacion con ID " + id + " no encontrada."));
    }
}
