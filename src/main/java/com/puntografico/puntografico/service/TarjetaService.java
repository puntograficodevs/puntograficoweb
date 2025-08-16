package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional
public class TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private TipoPapelTarjetaRepository tipoPapelTarjetaRepository;

    @Autowired
    private TipoColorTarjetaRepository tipoColorTarjetaRepository;

    @Autowired
    private TipoFazTarjetaRepository tipoFazTarjetaRepository;

    @Autowired
    private TipoLaminadoTarjetaRepository tipoLaminadoTarjetaRepository;

    @Autowired
    private MedidaTarjetaRepository medidaTarjetaRepository;

    @Autowired
    private CantidadTarjetaRepository cantidadTarjetaRepository;

    public Tarjeta crear(HttpServletRequest request) {
        String tipoPapelTarjetaString = request.getParameter("tipoPapelTarjeta.id");
        String tipoColorTarjetaString = request.getParameter("tipoColorTarjeta.id");
        String tipoFazTarjetaString = request.getParameter("tipoFazTarjeta.id");
        String tipoLaminadoTarjetaString = request.getParameter("tipoLaminadoTarjeta.id");
        String medidaTarjetaString = request.getParameter("medidaTarjeta.id");
        String cantidadTarjetaString = request.getParameter("cantidadTarjeta.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(tipoPapelTarjetaString, "tipoPapelTarjetaString es un dato obligatorio.");
        Assert.notNull(tipoColorTarjetaString, "tipoColorTarjetaString es un dato obligatorio.");
        Assert.notNull(tipoFazTarjetaString, "tipoFazTarjetaString es un dato obligatorio.");
        Assert.notNull(tipoLaminadoTarjetaString, "tipoLaminadoTarjetaString es un dato obligatorio.");
        Assert.notNull(medidaTarjetaString, "medidaTarjetaString es un dato obligatorio.");
        Assert.notNull(cantidadTarjetaString, "cantidadTarjetaString es un dato obligatorio.");

        TipoPapelTarjeta tipoPapelTarjeta = tipoPapelTarjetaRepository.findById(Long.parseLong(tipoPapelTarjetaString)).get();
        TipoColorTarjeta tipoColorTarjeta = tipoColorTarjetaRepository.findById(Long.parseLong(tipoColorTarjetaString)).get();
        TipoFazTarjeta tipoFazTarjeta = tipoFazTarjetaRepository.findById(Long.parseLong(tipoFazTarjetaString)).get();
        TipoLaminadoTarjeta tipoLaminadoTarjeta = tipoLaminadoTarjetaRepository.findById(Long.parseLong(tipoLaminadoTarjetaString)).get();
        MedidaTarjeta medidaTarjeta = medidaTarjetaRepository.findById(Long.parseLong(medidaTarjetaString)).get();
        CantidadTarjeta cantidadTarjeta = cantidadTarjetaRepository.findById(Long.parseLong(cantidadTarjetaString)).get();

        int cantidad;

        if (cantidadTarjeta.getId() != 9) {
            cantidad = Integer.parseInt(cantidadTarjeta.getCantidad());
        } else {
            cantidad = Integer.parseInt(cantidadString);
        }

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        tarjeta.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        tarjeta.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        tarjeta.setInformacionAdicional(request.getParameter("informacionAdicional"));
        tarjeta.setTipoPapelTarjeta(tipoPapelTarjeta);
        tarjeta.setTipoColorTarjeta(tipoColorTarjeta);
        tarjeta.setTipoFazTarjeta(tipoFazTarjeta);
        tarjeta.setTipoLaminadoTarjeta(tipoLaminadoTarjeta);
        tarjeta.setMedidaTarjeta(medidaTarjeta);
        tarjeta.setCantidadTarjeta(cantidadTarjeta);
        tarjeta.setCantidad(cantidad);

        return tarjetaRepository.save(tarjeta);
    }
}
