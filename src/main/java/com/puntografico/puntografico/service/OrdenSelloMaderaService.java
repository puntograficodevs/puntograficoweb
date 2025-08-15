package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.OrdenSelloMaderaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenSelloMaderaService {

    @Autowired
    private OrdenSelloMaderaRepository ordenSelloMaderaRepository;

    public OrdenSelloMadera crear(OrdenTrabajo ordenTrabajo, SelloMadera selloMadera) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(selloMadera, "Debe venir un sello para enlazar.");

        OrdenSelloMadera ordenSelloMadera = new OrdenSelloMadera();
        ordenSelloMadera.setSelloMadera(selloMadera);
        ordenSelloMadera.setOrdenTrabajo(ordenTrabajo);
        ordenSelloMadera.setCantidad(selloMadera.getCantidad());

        return ordenSelloMaderaRepository.save(ordenSelloMadera);
    }

    public OrdenSelloMadera buscarPorId(Long id) {
        return ordenSelloMaderaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenSelloMadera con ID " + id + " no encontrada."));
    }
}
