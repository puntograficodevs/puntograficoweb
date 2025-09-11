package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class TurneroService {

    private final MedidaTurneroRepository medidaTurneroRepository;

    private final TipoColorTurneroRepository tipoColorTurneroRepository;

    private final CantidadTurneroRepository cantidadTurneroRepository;

    private final TurneroRepository turneroRepository;

    public Turnero crear(HttpServletRequest request) {
        String cantidadHojasString = request.getParameter("cantidadHojas");
        String medidaTurneroString = request.getParameter("medidaTurnero.id");
        String tipoColorTurneroString = request.getParameter("tipoColorTurnero.id");
        String cantidadTurneroString = request.getParameter("cantidadTurnero.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(medidaTurneroString, "medidaTurneroString es un dato obligatorio.");
        Assert.notNull(tipoColorTurneroString, "tipoColorTurneroString es un dato obligatorio.");
        Assert.notNull(cantidadTurneroString, "cantidadTurneroString es un dato obligatorio.");
        Assert.notNull(cantidadHojasString, "cantidadHojas es un dato obligatorio.");

        MedidaTurnero medidaTurnero = medidaTurneroRepository.findById(Long.parseLong(medidaTurneroString)).get();
        TipoColorTurnero tipoColorTurnero = tipoColorTurneroRepository.findById(Long.parseLong(tipoColorTurneroString)).get();
        CantidadTurnero cantidadTurnero = cantidadTurneroRepository.findById(Long.parseLong(cantidadTurneroString)).get();

        if (cantidadString.isBlank() || cantidadString.isEmpty()) {
            cantidadString = cantidadTurnero.getCantidad();
        }

        Turnero Turnero = new Turnero();
        Turnero.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        Turnero.setCantidadHojas(Integer.parseInt(cantidadHojasString));
        Turnero.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        Turnero.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        Turnero.setInformacionAdicional(request.getParameter("informacionAdicional"));
        Turnero.setMedidaTurnero(medidaTurnero);
        Turnero.setTipoColorTurnero(tipoColorTurnero);
        Turnero.setCantidadTurnero(cantidadTurnero);
        Turnero.setCantidad(Integer.parseInt(cantidadString));

        return turneroRepository.save(Turnero);
    }
}
