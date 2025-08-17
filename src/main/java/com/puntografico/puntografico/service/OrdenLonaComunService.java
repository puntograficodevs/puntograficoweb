package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.LonaComun;
import com.puntografico.puntografico.domain.OrdenAgenda;
import com.puntografico.puntografico.domain.OrdenLonaComun;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.OrdenLonaComunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenLonaComunService {

    @Autowired
    private OrdenLonaComunRepository ordenLonaComunRepository;

    public OrdenLonaComun crear(OrdenTrabajo ordenTrabajo, LonaComun lonaComun) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(lonaComun, "Debe venir una lona común para enlazar.");

        OrdenLonaComun ordenLonaComun = new OrdenLonaComun();
        ordenLonaComun.setLonaComun(lonaComun);
        ordenLonaComun.setOrdenTrabajo(ordenTrabajo);
        ordenLonaComun.setCantidad(lonaComun.getCantidad());

        return ordenLonaComunRepository.save(ordenLonaComun);
    }

    public OrdenLonaComun buscarPorId(Long id) {
        return ordenLonaComunRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenLonaComun con ID " + id + " no encontrada."));
    }

    public OrdenLonaComun buscarPorOrdenId(Long id) {
        return ordenLonaComunRepository.findByOrdenTrabajo_Id(id);
    }
}
