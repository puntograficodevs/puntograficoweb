package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Agenda;
import com.puntografico.puntografico.domain.TipoColorAgenda;
import com.puntografico.puntografico.domain.TipoTapaAgenda;
import com.puntografico.puntografico.dto.AgendaDTO;
import com.puntografico.puntografico.repository.AgendaRepository;
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
    private OpcionesAgendaService opcionesAgendaService;

    public Agenda crear(AgendaDTO agendaDTO) {
        validarAgendaDTO(agendaDTO);

        TipoColorAgenda tipoColorAgenda = opcionesAgendaService.buscarTipoColorAgendaPorId(agendaDTO.getTipoColorAgendaId());
        TipoTapaAgenda tipoTapaAgenda = opcionesAgendaService.buscarTipoTapaAgendaPorId(agendaDTO.getTipoTapaAgendaId());

        Agenda agenda = new Agenda();
        agenda.setCantidadHojas(agendaDTO.getCantidadHojas());
        agenda.setMedida(agendaDTO.getMedida());
        agenda.setTipoTapaAgenda(tipoTapaAgenda);
        agenda.setTipoTapaPersonalizada(agenda.getTipoTapaPersonalizada());
        agenda.setTipoColorAgenda(tipoColorAgenda);
        agenda.setConAdicionalDisenio(agendaDTO.getConAdicionalDisenio());
        agenda.setEnlaceArchivo(agendaDTO.getEnlaceArchivo());
        agenda.setInformacionAdicional(agendaDTO.getInformacionAdicional());
        agenda.setCantidad(agendaDTO.getCantidad());

        return agendaRepository.save(agenda);
    }

    private void validarAgendaDTO(AgendaDTO agendaDTO) {
        Assert.notNull(agendaDTO.getCantidadHojas(), "La cantidad de hojas es un dato obligatorio.");
        Assert.notNull(agendaDTO.getTipoTapaAgendaId(), "El tipo de tapa es un dato obligatorio.");
        Assert.notNull(agendaDTO.getTipoColorAgendaId(), "El tipo de color es un dato obligatorio.");
        Assert.notNull(agendaDTO.getCantidad(), "La cantidad es un dato obligatorio.");
    }
}
