package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Agenda;
import com.puntografico.puntografico.domain.TipoColorAgenda;
import com.puntografico.puntografico.domain.TipoTapaAgenda;
import com.puntografico.puntografico.repository.AgendaRepository;
import com.puntografico.puntografico.repository.TipoColorAgendaRepository;
import com.puntografico.puntografico.repository.TipoTapaAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Transactional
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private TipoTapaAgendaRepository tipoTapaAgendaRepository;

    @Autowired
    private TipoColorAgendaRepository tipoColorAgendaRepository;

    public Agenda crear(HttpServletRequest request) {
        String cantidadHojas = request.getParameter("cantidadHojas");
        String tipoTapa = request.getParameter("tipoTapaAgenda.id");
        TipoTapaAgenda tipoTapaAgenda = tipoTapaAgendaRepository.findById(Long.parseLong(tipoTapa)).get();
        String tipoColor = request.getParameter("tipoColorAgenda.id");
        TipoColorAgenda tipoColorAgenda = tipoColorAgendaRepository.findById(Long.parseLong(tipoColor)).get();
        String cantidad = request.getParameter("cantidad");

        Assert.notNull(cantidadHojas, "La cantidad de hojas es un dato obligatorio.");
        Assert.notNull(tipoTapa, "El tipo de tapa es un dato obligatorio.");
        Assert.notNull(tipoColor, "El tipo de color es un dato obligatorio.");
        Assert.notNull(cantidad, "La cantidad es un dato obligatorio.");


        Agenda agenda = new Agenda();
        agenda.setCantidadHojas(Integer.parseInt(cantidadHojas));
        agenda.setMedida(request.getParameter("medida"));
        agenda.setTipoTapaAgenda(tipoTapaAgenda);
        agenda.setTipoTapaPersonalizada(request.getParameter("tipoTapaPersonalizada"));
        agenda.setTipoColorAgenda(tipoColorAgenda);
        agenda.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        agenda.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        agenda.setInformacionAdicional(request.getParameter("informacionAdicional"));
        agenda.setCantidad(Integer.parseInt(cantidad));

        return agendaRepository.save(agenda);
    }

}
