package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Agenda;
import com.puntografico.puntografico.domain.OrdenAgenda;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.OrdenAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenAgendaService {

    @Autowired
    private OrdenAgendaRepository ordenAgendaRepository;

    public OrdenAgenda crear(OrdenTrabajo ordenTrabajo, Agenda agenda) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(agenda, "Debe venir una agenda para enlazar.");

        OrdenAgenda ordenAgenda = new OrdenAgenda();
        ordenAgenda.setCantidad(agenda.getCantidad());
        ordenAgenda.setOrdenTrabajo(ordenTrabajo);
        ordenAgenda.setAgenda(agenda);

        return ordenAgendaRepository.save(ordenAgenda);
    }

    public OrdenAgenda buscarPorId(Long id) {
        return ordenAgendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenAgenda con ID " + id + " no encontrada"));
    }

    public OrdenAgenda buscarPorOrdenId(Long id) {
        return ordenAgendaRepository.findByOrdenTrabajo_Id(id);
    }
}
