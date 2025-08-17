package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.OrdenCatalogoRepository;
import com.puntografico.puntografico.repository.OrdenCierraBolsasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenCierraBolsasService {

    @Autowired
    private OrdenCierraBolsasRepository ordenCierraBolsasRepository;

    public OrdenCierraBolsas crear(OrdenTrabajo ordenTrabajo, CierraBolsas cierraBolsas) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(cierraBolsas, "Debe venir un cierra bolsas para enlazar");

        OrdenCierraBolsas ordenCierraBolsas = new OrdenCierraBolsas();
        ordenCierraBolsas.setCantidad(cierraBolsas.getCantidad());
        ordenCierraBolsas.setOrdenTrabajo(ordenTrabajo);
        ordenCierraBolsas.setCierraBolsas(cierraBolsas);

        return ordenCierraBolsasRepository.save(ordenCierraBolsas);
    }

    public OrdenCierraBolsas buscarPorId(Long id) {
        return ordenCierraBolsasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenCierraBolsas con ID " + id + " no encontrada"));
    }

    public OrdenCierraBolsas buscarPorOrdenId(Long id) {
        return ordenCierraBolsasRepository.findByOrdenTrabajo_Id(id);
    }
}
