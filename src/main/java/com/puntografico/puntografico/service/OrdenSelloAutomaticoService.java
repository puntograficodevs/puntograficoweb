package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.OrdenRotulacionRepository;
import com.puntografico.puntografico.repository.OrdenSelloAutomaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenSelloAutomaticoService {

    @Autowired
    private OrdenSelloAutomaticoRepository ordenSelloAutomaticoRepository;

    public OrdenSelloAutomatico crear(OrdenTrabajo ordenTrabajo, SelloAutomatico selloAutomatico) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(selloAutomatico, "Debe venir un sello para enlazar.");

        OrdenSelloAutomatico ordenSelloAutomatico = new OrdenSelloAutomatico();
        ordenSelloAutomatico.setSelloAutomatico(selloAutomatico);
        ordenSelloAutomatico.setOrdenTrabajo(ordenTrabajo);
        ordenSelloAutomatico.setCantidad(selloAutomatico.getCantidad());

        return ordenSelloAutomaticoRepository.save(ordenSelloAutomatico);
    }

    public OrdenSelloAutomatico buscarPorId(Long id) {
        return ordenSelloAutomaticoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenSelloAutomatico con ID " + id + " no encontrada."));
    }

    public OrdenSelloAutomatico buscarPorOrdenId(Long id) {
        return ordenSelloAutomaticoRepository.findByOrdenTrabajo_Id(id);
    }
}
