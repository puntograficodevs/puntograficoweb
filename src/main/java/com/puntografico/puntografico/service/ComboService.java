package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Combo;
import com.puntografico.puntografico.domain.TipoCombo;
import com.puntografico.puntografico.repository.ComboRepository;
import com.puntografico.puntografico.repository.TipoComboRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Transactional @AllArgsConstructor
public class ComboService {

    private final ComboRepository comboRepository;

    private final TipoComboRepository tipoComboRepository;

    public Combo crear(HttpServletRequest request) {
        String tipoComboString = request.getParameter("tipoCombo.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(tipoComboString, "El tipo de combo es un dato obligatorio.");
        Assert.notNull(cantidadString, "La cantidad es un dato obligatorio.");

        Combo combo = new Combo();
        TipoCombo tipoCombo = tipoComboRepository.findById(Long.parseLong(tipoComboString)).get();
        combo.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        combo.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        combo.setInformacionAdicional(request.getParameter("informacionAdicional"));
        combo.setTipoCombo(tipoCombo);
        combo.setCantidad(Integer.parseInt(cantidadString));

        return comboRepository.save(combo);
    }
}
