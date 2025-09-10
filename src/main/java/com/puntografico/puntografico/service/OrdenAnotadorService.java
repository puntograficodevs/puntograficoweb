package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Anotador;
import com.puntografico.puntografico.domain.OrdenAgenda;
import com.puntografico.puntografico.domain.OrdenAnotador;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.OrdenAnotadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrdenAnotadorService {

    @Autowired
    private OrdenAnotadorRepository ordenAnotadorRepository;

    public OrdenAnotador guardar(OrdenTrabajo ordenTrabajo, Anotador anotador, Long idOrdenAnotador) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(anotador, "Debe venir una agenda para enlazar");

        OrdenAnotador ordenAnotador = (idOrdenAnotador != null) ? ordenAnotadorRepository.findById(idOrdenAnotador).get() : new OrdenAnotador();
        ordenAnotador.setCantidad(anotador.getCantidad());
        ordenAnotador.setOrdenTrabajo(ordenTrabajo);
        ordenAnotador.setAnotador(anotador);

        return ordenAnotadorRepository.save(ordenAnotador);
    }

    public OrdenAnotador buscarPorId(Long id) {
        return ordenAnotadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenAnotador con ID " + id + " no encontrada"));
    }

    public OrdenAnotador buscarPorOrdenId(Long id) {
        return ordenAnotadorRepository.findByOrdenTrabajo_Id(id);
    }
}
