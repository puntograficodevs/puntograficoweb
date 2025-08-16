package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Otro;
import com.puntografico.puntografico.domain.OrdenOtro;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.OrdenOtroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenOtroService {

    @Autowired
    private OrdenOtroRepository ordenOtroRepository;

    public OrdenOtro crear(OrdenTrabajo ordenTrabajo, Otro otro) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(otro, "Debe venir algo para enlazar.");

        OrdenOtro ordenOtro = new OrdenOtro();
        ordenOtro.setCantidad(otro.getCantidad());
        ordenOtro.setOrdenTrabajo(ordenTrabajo);
        ordenOtro.setOtro(otro);

        return ordenOtroRepository.save(ordenOtro);
    }

    public OrdenOtro buscarPorId(Long id) {
        return ordenOtroRepository.findById(id).
                orElseThrow(() -> new RuntimeException("OrdenOtro con ID " + id + " no encontrada."));
    }
}
