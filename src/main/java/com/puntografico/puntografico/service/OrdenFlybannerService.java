package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Flybanner;
import com.puntografico.puntografico.domain.OrdenFlybanner;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.OrdenFlybannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrdenFlybannerService {

    @Autowired
    private OrdenFlybannerRepository ordenFlybannerRepository;

    public OrdenFlybanner crear(OrdenTrabajo ordenTrabajo, Flybanner flybanner) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden para enlazar.");
        Assert.notNull(flybanner, "Debe venir un flybanner para enlazar.");

        OrdenFlybanner ordenFlybanner = new OrdenFlybanner();
        ordenFlybanner.setCantidad(flybanner.getCantidad());
        ordenFlybanner.setOrdenTrabajo(ordenTrabajo);
        ordenFlybanner.setFlybanner(flybanner);

        return ordenFlybannerRepository.save(ordenFlybanner);
    }

    public OrdenFlybanner buscarPorId(Long id) {
        return ordenFlybannerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenFlybanner con ID " + id + " no encontrada"));
    }
}
