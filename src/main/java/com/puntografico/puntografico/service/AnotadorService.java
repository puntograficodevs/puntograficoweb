package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Anotador;
import com.puntografico.puntografico.repository.AnotadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Transactional
public class AnotadorService {

    @Autowired
    private AnotadorRepository anotadorRepository;

    public Anotador crear(HttpServletRequest request) {
        String cantidadHojas = request.getParameter("cantidadHojas");
        String cantidad = request.getParameter("cantidad");
        String tipoTapa = request.getParameter("tipoTapa");

        Assert.notNull(cantidadHojas, "La cantidad de hojas es un dato obligatorio.");
        Assert.notNull(tipoTapa, "El tipo de tapa es un dato obligatorio.");
        Assert.notNull(cantidad, "La cantidad es un dato obligatorio.");


        Anotador anotador = new Anotador();
        anotador.setCantidadHojas(Integer.parseInt(cantidadHojas));
        anotador.setMedida(request.getParameter("medida"));
        anotador.setTipoTapa(tipoTapa);
        anotador.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        anotador.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        anotador.setInformacionAdicional(request.getParameter("informacionAdicional"));
        anotador.setCantidad(Integer.parseInt(cantidad));

        return anotadorRepository.save(anotador);
    }
}
