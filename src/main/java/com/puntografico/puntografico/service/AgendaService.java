package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Agenda;
import com.puntografico.puntografico.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service
@Transactional
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public Agenda guardar(Agenda agenda) {
        Assert.notNull(agenda.getMedida(), "La medida es un campo obligatorio.");
        Assert.notNull(agenda.getCantidadHojas(), "La cantidad de hojas es un campo obligatorio.");
        Assert.notNull(agenda.getTipoColorAgenda(), "El tipo de color es un campo obligatorio.");
        Assert.notNull(agenda.getTipoTapaAgenda(), "El tipo de tapa es un campo obligatorio.");

        return agendaRepository.save(agenda);
    }
}
