package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CuadernoAnillado;
import com.puntografico.puntografico.domain.OrdenCuadernoAnillado;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.OrdenCuadernoAnilladoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenCuadernoAnilladoService {

    @Autowired
    private OrdenCuadernoAnilladoRepository ordenCuadernoAnilladoRepository;

    public OrdenCuadernoAnillado crear(OrdenTrabajo ordenTrabajo, CuadernoAnillado cuadernoAnillado) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(cuadernoAnillado, "Debe venir un cuaderno anillado para enlazar.");

        OrdenCuadernoAnillado ordenCuadernoAnillado = new OrdenCuadernoAnillado();
        ordenCuadernoAnillado.setCantidad(cuadernoAnillado.getCantidad());
        ordenCuadernoAnillado.setOrdenTrabajo(ordenTrabajo);
        ordenCuadernoAnillado.setCuadernoAnillado(cuadernoAnillado);

        return ordenCuadernoAnilladoRepository.save(ordenCuadernoAnillado);
    }

    public OrdenCuadernoAnillado buscarPorId(Long id) {
        return ordenCuadernoAnilladoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenCuadernoAnillado con ID " + id + "no encontrada"));
    }
}
