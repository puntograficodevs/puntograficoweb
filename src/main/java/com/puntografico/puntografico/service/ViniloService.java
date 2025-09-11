package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Transactional @AllArgsConstructor
public class ViniloService {

    private final ViniloRepository viniloRepository;

    private final TipoViniloRepository tipoViniloRepository;

    private final TipoAdicionalViniloRepository tipoAdicionalViniloRepository;

    private final TipoCorteViniloRepository tipoCorteViniloRepository;

    private final MedidaViniloRepository medidaViniloRepository;

    private final CantidadViniloRepository cantidadViniloRepository;

    public Vinilo crear(HttpServletRequest request) {
        String tipoViniloString = request.getParameter("tipoVinilo.id");
        String tipoAdicionalViniloString = request.getParameter("tipoAdicionalVinilo.id");
        String tipoCorteViniloString = request.getParameter("tipoCorteVinilo.id");
        String medidaViniloString = request.getParameter("medidaVinilo.id");
        String cantidadViniloString = request.getParameter("cantidadVinilo.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(tipoViniloString, "tipoViniloString es un dato obligatorio.");
        Assert.notNull(tipoAdicionalViniloString, "tipoAdicionalViniloString es un dato obligatorio.");
        Assert.notNull(tipoCorteViniloString, "tipoCorteViniloString es un dato obligatorio.");
        Assert.notNull(medidaViniloString, "medidaViniloString es un dato obligatorio.");
        Assert.notNull(cantidadViniloString, "cantidadViniloString es un dato obligatorio.");

        TipoVinilo tipoVinilo = tipoViniloRepository.findById(Long.parseLong(tipoViniloString)).get();
        TipoAdicionalVinilo tipoAdicionalVinilo = tipoAdicionalViniloRepository.findById(Long.parseLong(tipoAdicionalViniloString)).get();
        TipoCorteVinilo tipoCorteVinilo = tipoCorteViniloRepository.findById(Long.parseLong(tipoCorteViniloString)).get();
        MedidaVinilo medidaVinilo = medidaViniloRepository.findById(Long.parseLong(medidaViniloString)).get();
        CantidadVinilo cantidadVinilo = cantidadViniloRepository.findById(Long.parseLong(cantidadViniloString)).get();

        int cantidad;

        if (cantidadVinilo.getId() != 3) {
            cantidad = Integer.parseInt(cantidadVinilo.getCantidad());
        } else {
            cantidad = Integer.parseInt(cantidadString);
        }

        Vinilo vinilo = new Vinilo();
        vinilo.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        vinilo.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        vinilo.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        vinilo.setInformacionAdicional(request.getParameter("informacionAdicional"));
        vinilo.setTipoVinilo(tipoVinilo);
        vinilo.setTipoAdicionalVinilo(tipoAdicionalVinilo);
        vinilo.setTipoCorteVinilo(tipoCorteVinilo);
        vinilo.setMedidaVinilo(medidaVinilo);
        vinilo.setCantidadVinilo(cantidadVinilo);
        vinilo.setCantidad(cantidad);

        return viniloRepository.save(vinilo);
    }
}
