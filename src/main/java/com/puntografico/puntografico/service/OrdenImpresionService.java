package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Impresion;
import com.puntografico.puntografico.domain.OrdenImpresion;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.OrdenImpresionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class OrdenImpresionService {

    private final OrdenImpresionRepository ordenImpresionRepository;

    public OrdenImpresion crear(OrdenTrabajo ordenTrabajo, Impresion impresion) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(impresion, "Debe venir una impresión para enlazar.");

        OrdenImpresion ordenImpresion = new OrdenImpresion();
        ordenImpresion.setCantidad(impresion.getCantidad());
        ordenImpresion.setOrdenTrabajo(ordenTrabajo);
        ordenImpresion.setImpresion(impresion);

        return ordenImpresionRepository.save(ordenImpresion);
    }

    public OrdenImpresion buscarPorId(Long id) {
        return ordenImpresionRepository.findById(id).
                orElseThrow(() -> new RuntimeException("OrdenImpresion con ID " + id + " no encontrada."));
    }

    public OrdenImpresion buscarPorOrdenId(Long id) {
        return ordenImpresionRepository.findByOrdenTrabajo_Id(id);
    }
}
