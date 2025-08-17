package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Folleto;
import com.puntografico.puntografico.domain.OrdenAgenda;
import com.puntografico.puntografico.domain.OrdenFolleto;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.OrdenFolletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenFolletoService {

    @Autowired
    private OrdenFolletoRepository ordenFolletoRepository;

    public OrdenFolleto crear(OrdenTrabajo ordenTrabajo, Folleto folleto) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(folleto, "Debe venir un folleto para enlazar.");

        OrdenFolleto ordenFolleto = new OrdenFolleto();
        ordenFolleto.setCantidad(folleto.getCantidad());
        ordenFolleto.setOrdenTrabajo(ordenTrabajo);
        ordenFolleto.setFolleto(folleto);

        return ordenFolletoRepository.save(ordenFolleto);
    }

    public OrdenFolleto buscarPorId(Long id) {
        return ordenFolletoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenFolleto con ID " + id + " no encontrada"));
    }

    public OrdenFolleto buscarPorOrdenId(Long id) {
        return ordenFolletoRepository.findByOrdenTrabajo_Id(id);
    }
}
