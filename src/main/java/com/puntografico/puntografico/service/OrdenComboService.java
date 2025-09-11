package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Combo;
import com.puntografico.puntografico.domain.OrdenCombo;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.OrdenComboRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class OrdenComboService {

    private final OrdenComboRepository ordenComboRepository;

    public OrdenCombo crear(OrdenTrabajo ordenTrabajo, Combo combo) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(combo, "Debe venir un combo para enlazar.");

        OrdenCombo ordenCombo = new OrdenCombo();
        ordenCombo.setCantidad(combo.getCantidad());
        ordenCombo.setOrdenTrabajo(ordenTrabajo);
        ordenCombo.setCombo(combo);

        return ordenComboRepository.save(ordenCombo);
    }

    public OrdenCombo buscarPorId(Long id) {
        return ordenComboRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenCombo con ID " + id + " no encontrada"));
    }

    public OrdenCombo buscarPorOrdenId(Long id) {
        return ordenComboRepository.findByOrdenTrabajo_Id(id);
    }
}
