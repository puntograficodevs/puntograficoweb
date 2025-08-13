package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.HojasMembreteadas;
import com.puntografico.puntografico.domain.OrdenHojasMembreteadas;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.OrdenHojasMembreteadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenHojasMembreteadasService {

    @Autowired
    private OrdenHojasMembreteadasRepository ordenHojasMembreteadasRepository;

    public OrdenHojasMembreteadas crear(OrdenTrabajo ordenTrabajo, HojasMembreteadas hojasMembreteadas) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(hojasMembreteadas, "Debe venir una hoja membreteada para enlazar.");

        OrdenHojasMembreteadas ordenHojasMembreteadas = new OrdenHojasMembreteadas();
        ordenHojasMembreteadas.setCantidad(hojasMembreteadas.getCantidad());
        ordenHojasMembreteadas.setOrdenTrabajo(ordenTrabajo);
        ordenHojasMembreteadas.setHojasMembreteadas(hojasMembreteadas);

        return ordenHojasMembreteadasRepository.save(ordenHojasMembreteadas);
    }

    public OrdenHojasMembreteadas buscarPorId(Long id) {
        return ordenHojasMembreteadasRepository.findById(id).orElseThrow(() -> new RuntimeException("OrdenHojasMembreteadas con ID " + id + " no encontrada."));
    }

}
