package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.domain.OrdenViniloDeCorte;
import com.puntografico.puntografico.domain.ViniloDeCorte;
import com.puntografico.puntografico.repository.OrdenViniloDeCorteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenViniloDeCorteService {

    @Autowired
    private OrdenViniloDeCorteRepository ordenViniloDeCorteRepository;

    public OrdenViniloDeCorte crear(OrdenTrabajo ordenTrabajo, ViniloDeCorte viniloDeCorte) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(viniloDeCorte, "Debe venir un vinilo de corte para enlazar.");

        OrdenViniloDeCorte ordenViniloDeCorte = new OrdenViniloDeCorte();
        ordenViniloDeCorte.setCantidad(viniloDeCorte.getCantidad());
        ordenViniloDeCorte.setOrdenTrabajo(ordenTrabajo);
        ordenViniloDeCorte.setViniloDeCorte(viniloDeCorte);

        return ordenViniloDeCorteRepository.save(ordenViniloDeCorte);
    }

    public OrdenViniloDeCorte buscarPorId(Long id) {
        return ordenViniloDeCorteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenViniloDeCorte con ID " + id + " no encontrada"));
    }
}
