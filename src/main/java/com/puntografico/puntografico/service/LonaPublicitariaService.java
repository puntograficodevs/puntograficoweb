package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class LonaPublicitariaService {

    private final LonaPublicitariaRepository lonaPublicitariaRepository;

    private final MedidaLonaPublicitariaRepository medidaLonaPublicitariaRepository;

    private final TipoLonaPublicitariaRepository tipoLonaPublicitariaRepository;

    public LonaPublicitaria crear(HttpServletRequest request) {
        String medidaLonaPublicitariaString = request.getParameter("medidaLonaPublicitaria.id");
        String tipoLonaPublicitariaString = request.getParameter("tipoLonaPublicitaria.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(medidaLonaPublicitariaString, "La medida es un dato obligatorio.");
        Assert.notNull(tipoLonaPublicitariaString, "El tipo de lona es un dato obligatorio.");
        Assert.notNull(cantidadString, "La cantidad es un dato obligatorio.");

        MedidaLonaPublicitaria medidaLonaPublicitaria = medidaLonaPublicitariaRepository.findById(Long.parseLong(medidaLonaPublicitariaString)).get();
        TipoLonaPublicitaria tipoLonaPublicitaria = tipoLonaPublicitariaRepository.findById(Long.parseLong(tipoLonaPublicitariaString)).get();

        LonaPublicitaria lonaPublicitaria = new LonaPublicitaria();
        lonaPublicitaria.setMedidaLonaPublicitaria(medidaLonaPublicitaria);
        lonaPublicitaria.setTipoLonaPublicitaria(tipoLonaPublicitaria);
        lonaPublicitaria.setConAdicionalPortabanner(request.getParameter("conAdicionalPortabanner") != null);
        lonaPublicitaria.setConOjales(request.getParameter("conOjales") != null);
        lonaPublicitaria.setConOjalesConRefuerzo(request.getParameter("conOjalesConRefuerzo") != null);
        lonaPublicitaria.setConBolsillos(request.getParameter("conBolsillos") != null);
        lonaPublicitaria.setConDemasiaParaTensado(request.getParameter("conDemasiaParaTensado") != null);
        lonaPublicitaria.setConSolapado(request.getParameter("conSolapado") != null);
        lonaPublicitaria.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        lonaPublicitaria.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        lonaPublicitaria.setInformacionAdicional(request.getParameter("informacionAdicional"));
        lonaPublicitaria.setCantidad(Integer.parseInt(cantidadString));

        return lonaPublicitariaRepository.save(lonaPublicitaria);
    }
}
