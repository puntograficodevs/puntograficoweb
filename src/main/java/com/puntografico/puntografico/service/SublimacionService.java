package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadSublimacion;
import com.puntografico.puntografico.domain.MaterialSublimacion;
import com.puntografico.puntografico.domain.Sublimacion;
import com.puntografico.puntografico.repository.CantidadSublimacionRepository;
import com.puntografico.puntografico.repository.MaterialSublimacionRepository;
import com.puntografico.puntografico.repository.SublimacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional
public class SublimacionService {

    @Autowired
    private SublimacionRepository sublimacionRepository;

    @Autowired
    private MaterialSublimacionRepository materialSublimacionRepository;

    @Autowired
    private CantidadSublimacionRepository cantidadSublimacionRepository;

    public Sublimacion crear(HttpServletRequest request) {
        String materialSublimacionString = request.getParameter("materialSublimacion.id");
        String cantidadSublimacionString = request.getParameter("cantidadSublimacion.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(materialSublimacionString, "materialSublimacionString es un dato obligatorio.");
        Assert.notNull(cantidadSublimacionString, "cantidadSublimacionString es un dato obligatorio.");

        MaterialSublimacion materialSublimacion = materialSublimacionRepository.findById(Long.parseLong(materialSublimacionString)).get();
        CantidadSublimacion cantidadSublimacion = cantidadSublimacionRepository.findById(Long.parseLong(cantidadSublimacionString)).get();

        int cantidad;

        if (cantidadSublimacion.getId() != 6) {
            cantidad = Integer.parseInt(cantidadSublimacion.getCantidad());
        } else {
            cantidad = Integer.parseInt(cantidadString);
        }

        Sublimacion sublimacion = new Sublimacion();
        sublimacion.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        sublimacion.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        sublimacion.setInformacionAdicional(request.getParameter("informacionAdicional"));
        sublimacion.setMaterialSublimacion(materialSublimacion);
        sublimacion.setCantidadSublimacion(cantidadSublimacion);
        sublimacion.setCantidad(cantidad);

        return sublimacionRepository.save(sublimacion);
    }
}
