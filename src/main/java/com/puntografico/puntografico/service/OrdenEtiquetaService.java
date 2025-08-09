package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Etiqueta;
import com.puntografico.puntografico.domain.OrdenEtiqueta;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.OrdenEtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenEtiquetaService {

    @Autowired
    private OrdenEtiquetaRepository ordenEtiquetaRepository;

    public OrdenEtiqueta crear(OrdenTrabajo ordenTrabajo, Etiqueta etiqueta) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(etiqueta, "Debe venir una etiqueta para enlazar.");

        OrdenEtiqueta ordenEtiqueta = new OrdenEtiqueta();
        ordenEtiqueta.setCantidad(etiqueta.getCantidad());
        ordenEtiqueta.setOrdenTrabajo(ordenTrabajo);
        ordenEtiqueta.setEtiqueta(etiqueta);

        return ordenEtiquetaRepository.save(ordenEtiqueta);
    }

    public OrdenEtiqueta buscarPorId(Long id) {
        return ordenEtiquetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenEtiqueta con ID " + id + " no encontrada"));
    }
}
