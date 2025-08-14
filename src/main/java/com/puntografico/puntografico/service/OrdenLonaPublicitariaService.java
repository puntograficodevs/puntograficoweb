package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.LonaPublicitaria;
import com.puntografico.puntografico.domain.OrdenLonaPublicitaria;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.OrdenLonaPublicitariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenLonaPublicitariaService {

    @Autowired
    private OrdenLonaPublicitariaRepository ordenLonaPublicitariaRepository;

    public OrdenLonaPublicitaria crear(OrdenTrabajo ordenTrabajo, LonaPublicitaria lonaPublicitaria) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(lonaPublicitaria, "Debe venir una lona publicitaria para enlazar.");

        OrdenLonaPublicitaria ordenLonaPublicitaria = new OrdenLonaPublicitaria();
        ordenLonaPublicitaria.setLonaPublicitaria(lonaPublicitaria);
        ordenLonaPublicitaria.setOrdenTrabajo(ordenTrabajo);
        ordenLonaPublicitaria.setCantidad(lonaPublicitaria.getCantidad());

        return ordenLonaPublicitariaRepository.save(ordenLonaPublicitaria);
    }

    public OrdenLonaPublicitaria buscarPorId(Long id) {
        return ordenLonaPublicitariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenLonaPublicitaria con ID " + id + " no encontrada."));
    }
}
