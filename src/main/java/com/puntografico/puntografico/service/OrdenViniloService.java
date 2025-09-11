package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.OrdenViniloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service
@Transactional @AllArgsConstructor
public class OrdenViniloService {

    private final OrdenViniloRepository ordenViniloRepository;

    public OrdenVinilo crear(OrdenTrabajo ordenTrabajo, Vinilo vinilo) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(vinilo, "Debe venir un vinilo para enlazar.");

        OrdenVinilo ordenVinilo = new OrdenVinilo();
        ordenVinilo.setCantidad(vinilo.getCantidad());
        ordenVinilo.setOrdenTrabajo(ordenTrabajo);
        ordenVinilo.setVinilo(vinilo);

        return ordenViniloRepository.save(ordenVinilo);
    }

    public OrdenVinilo buscarPorId(Long id) {
        return ordenViniloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenVinilo con ID " + id + " no encontrada"));
    }

    public OrdenVinilo buscarPorOrdenId(Long id) {
        return ordenViniloRepository.findByOrdenTrabajo_Id(id);
    }
}
