package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.OrdenTarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenTarjetaService {

    @Autowired
    private OrdenTarjetaRepository ordenTarjetaRepository;

    public OrdenTarjeta crear(OrdenTrabajo ordenTrabajo, Tarjeta tarjeta) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(tarjeta, "Debe venir una tarjeta para enlazar.");

        OrdenTarjeta ordenTarjeta = new OrdenTarjeta();
        ordenTarjeta.setCantidad(tarjeta.getCantidad());
        ordenTarjeta.setOrdenTrabajo(ordenTrabajo);
        ordenTarjeta.setTarjeta(tarjeta);

        return ordenTarjetaRepository.save(ordenTarjeta);
    }

    public OrdenTarjeta buscarPorId(Long id) {
        return ordenTarjetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenTarjeta con ID " + id + " no encontrada"));
    }

    public OrdenTarjeta buscarPorOrdenId(Long id) {
        return ordenTarjetaRepository.findByOrdenTrabajo_Id(id);
    }
}
