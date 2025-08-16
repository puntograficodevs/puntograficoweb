package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.OrdenSublimacion;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.domain.Sublimacion;
import com.puntografico.puntografico.repository.OrdenSublimacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenSublimacionService {

    @Autowired
    private OrdenSublimacionRepository ordenSublimacionRepository;

    public OrdenSublimacion crear(OrdenTrabajo ordenTrabajo, Sublimacion sublimacion) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(sublimacion, "Debe venir un sublimacion para enlazar.");

        OrdenSublimacion ordenSublimacion = new OrdenSublimacion();
        ordenSublimacion.setCantidad(sublimacion.getCantidad());
        ordenSublimacion.setOrdenTrabajo(ordenTrabajo);
        ordenSublimacion.setSublimacion(sublimacion);

        return ordenSublimacionRepository.save(ordenSublimacion);
    }

    public OrdenSublimacion buscarPorId(Long id) {
        return ordenSublimacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenSublimacion con ID " + id + " no encontrada"));
    }
}
