package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.OrdenCatalogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional
public class OrdenCatalogoService {

    @Autowired
    private OrdenCatalogoRepository ordenCatalogoRepository;

    public OrdenCatalogo crear(OrdenTrabajo ordenTrabajo, Catalogo catalogo) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(catalogo, "Debe venir un catálogo para enlazar");

        OrdenCatalogo ordenCatalogo = new OrdenCatalogo();
        ordenCatalogo.setCantidad(catalogo.getCantidad());
        ordenCatalogo.setOrdenTrabajo(ordenTrabajo);
        ordenCatalogo.setCatalogo(catalogo);

        return ordenCatalogoRepository.save(ordenCatalogo);
    }

    public OrdenCatalogo buscarPorId(Long id) {
        return ordenCatalogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenCatalogo con ID " + id + " no encontrada"));
    }

    public OrdenCatalogo buscarPorOrdenId(Long id) {
        return ordenCatalogoRepository.findByOrdenTrabajo_Id(id);
    }
}
