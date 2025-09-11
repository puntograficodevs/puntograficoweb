package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.OrdenAgendaRepository;
import com.puntografico.puntografico.repository.OrdenCarpetaSolapaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class OrdenCarpetaSolapaService {

    private final OrdenCarpetaSolapaRepository ordenCarpetaSolapaRepository;

    public OrdenCarpetaSolapa crear(OrdenTrabajo ordenTrabajo, CarpetaSolapa carpetaSolapa) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(carpetaSolapa, "Debe venir una carpeta con solapas para enlazar.");

        OrdenCarpetaSolapa ordenCarpetaSolapa = new OrdenCarpetaSolapa();
        ordenCarpetaSolapa.setCantidad(carpetaSolapa.getCantidad());
        ordenCarpetaSolapa.setOrdenTrabajo(ordenTrabajo);
        ordenCarpetaSolapa.setCarpetaSolapa(carpetaSolapa);

        return ordenCarpetaSolapaRepository.save(ordenCarpetaSolapa);
    }

    public OrdenCarpetaSolapa buscarPorId(Long id) {
        return ordenCarpetaSolapaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenCarpetaSolapa con ID " + id + " no encontrada"));
    }

    public OrdenCarpetaSolapa buscarPorOrdenId(Long id) {
        return ordenCarpetaSolapaRepository.findByOrdenTrabajo_Id(id);
    }
}
