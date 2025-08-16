package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.domain.OrdenViniloPlasticoCorrugado;
import com.puntografico.puntografico.domain.ViniloPlasticoCorrugado;
import com.puntografico.puntografico.repository.OrdenViniloPlasticoCorrugadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenViniloPlasticoCorrugadoService {

    @Autowired
    private OrdenViniloPlasticoCorrugadoRepository ordenViniloPlasticoCorrugadoRepository;

    public OrdenViniloPlasticoCorrugado crear(OrdenTrabajo ordenTrabajo, ViniloPlasticoCorrugado viniloPlasticoCorrugado) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(viniloPlasticoCorrugado, "Debe venir un vinilo plastico corrugado para enlazar.");

        OrdenViniloPlasticoCorrugado ordenViniloPlasticoCorrugado = new OrdenViniloPlasticoCorrugado();
        ordenViniloPlasticoCorrugado.setCantidad(viniloPlasticoCorrugado.getCantidad());
        ordenViniloPlasticoCorrugado.setOrdenTrabajo(ordenTrabajo);
        ordenViniloPlasticoCorrugado.setViniloPlasticoCorrugado(viniloPlasticoCorrugado);

        return ordenViniloPlasticoCorrugadoRepository.save(ordenViniloPlasticoCorrugado);
    }

    public OrdenViniloPlasticoCorrugado buscarPorId(Long id) {
        return ordenViniloPlasticoCorrugadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenViniloPlasticoCorrugado con ID " + id + " no encontrada"));
    }
}
