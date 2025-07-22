package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Agenda;
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
        Agenda agenda = new Agenda();

        agenda.setMedida(request.getParameter("medida"));

        String cantHojasStr = request.getParameter("cantidadHojas");
        agenda.setCantidadHojas(cantHojasStr != null ? Integer.parseInt(cantHojasStr) : 0);

        String tipoTapaIdStr = request.getParameter("tipoTapaAgenda.id");
        if (tipoTapaIdStr != null && !tipoTapaIdStr.isBlank()) {
            Long tipoTapaId = Long.parseLong(tipoTapaIdStr);
            agenda.setTipoTapaAgenda(tipoTapaAgendaRepository.findById(tipoTapaId)
                    .orElseThrow(() -> new RuntimeException("Tipo tapa agenda no encontrado")));
        } else {
            throw new RuntimeException("Tipo tapa agenda es obligatorio");
        }

        agenda.setTipoTapaPersonalizada(request.getParameter("tipoTapaPersonalizada"));

        String tipoColorIdStr = request.getParameter("tipoColorAgenda.id");
        if (tipoColorIdStr != null && !tipoColorIdStr.isBlank()) {
            Long tipoColorId = Long.parseLong(tipoColorIdStr);
            agenda.setTipoColorAgenda(tipoColorAgendaRepository.findById(tipoColorId)
                    .orElseThrow(() -> new RuntimeException("Tipo color agenda no encontrado")));
        } else {
            throw new RuntimeException("Tipo color agenda es obligatorio");
        }

        agenda.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);

        agenda.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        agenda.setInformacionAdicional(request.getParameter("informacionAdicional"));

        String precioStr = request.getParameter("precio");
        agenda.setPrecio(precioStr != null ? Integer.parseInt(precioStr) : 0);

        return agendaRepository.save(agenda);
    }

}
