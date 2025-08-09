package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private TipoPapelEntradaRepository tipoPapelEntradaRepository;

    @Autowired
    private TipoColorEntradaRepository tipoColorEntradaRepository;

    @Autowired
    private TipoTroqueladoEntradaRepository tipoTroqueladoEntradaRepository;

    @Autowired
    private MedidaEntradaRepository medidaEntradaRepository;

    @Autowired
    private CantidadEntradaRepository cantidadEntradaRepository;

    @Autowired
    private NumeradoEntradaRepository numeradoEntradaRepository;

    @Autowired
    private TerminacionEntradaRepository terminacionEntradaRepository;

    public Entrada crear(HttpServletRequest request) {
        String tipoPapelEntradaString = request.getParameter("tipoPapelEntrada.id");
        String tipoColorEntradaString = request.getParameter("tipoColorEntrada.id");
        String tipoTroqueladoEntradaString = request.getParameter("tipoTroqueladoEntrada.id");
        String medidaEntradaString = request.getParameter("medidaEntrada.id");
        String cantidadEntradaString = request.getParameter("cantidadEntrada.id");
        String numeradoEntradaString = request.getParameter("numeradoEntrada.id");
        String terminacionEntradaString = request.getParameter("terminacionEntrada.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(tipoPapelEntradaString, "El tipo de papel es un dato obligatorio.");
        Assert.notNull(tipoColorEntradaString, "El tipo de color es un dato obligatorio.");
        Assert.notNull(tipoTroqueladoEntradaString, "El tipo de troquelado es un dato obligatorio.");
        Assert.notNull(medidaEntradaString, "La medida es un dato obligatorio.");
        Assert.notNull(numeradoEntradaString, "El numerado es un dato obligatorio.");
        Assert.notNull(terminacionEntradaString, "La terminación es un dato obligatorio.");
        Assert.notNull(cantidadString, "La cantidad es un dato obligatorio.");

        Entrada entrada = new Entrada();
        TipoPapelEntrada tipoPapelEntrada = tipoPapelEntradaRepository.findById(Long.parseLong(tipoPapelEntradaString)).get();
        TipoColorEntrada tipoColorEntrada = tipoColorEntradaRepository.findById(Long.parseLong(tipoColorEntradaString)).get();
        TipoTroqueladoEntrada tipoTroqueladoEntrada = tipoTroqueladoEntradaRepository.findById(Long.parseLong(tipoTroqueladoEntradaString)).get();
        MedidaEntrada medidaEntrada = medidaEntradaRepository.findById(Long.parseLong(medidaEntradaString)).get();
        CantidadEntrada cantidadEntrada = cantidadEntradaRepository.findById(Long.parseLong(cantidadEntradaString)).get();
        NumeradoEntrada numeradoEntrada = numeradoEntradaRepository.findById(Long.parseLong(numeradoEntradaString)).get();
        TerminacionEntrada terminacionEntrada = terminacionEntradaRepository.findById(Long.parseLong(terminacionEntradaString)).get();

        if (cantidadString.isBlank() || cantidadString.isEmpty()) {
            cantidadString = cantidadEntrada.getCantidad();
        }

        entrada.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        entrada.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        entrada.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        entrada.setInformacionAdicional(request.getParameter("informacionAdicional"));
        entrada.setTipoPapelEntrada(tipoPapelEntrada);
        entrada.setTipoColorEntrada(tipoColorEntrada);
        entrada.setTipoTroqueladoEntrada(tipoTroqueladoEntrada);
        entrada.setMedidaEntrada(medidaEntrada);
        entrada.setCantidadEntrada(cantidadEntrada);
        entrada.setNumeradoEntrada(numeradoEntrada);
        entrada.setTerminacionEntrada(terminacionEntrada);
        entrada.setCantidad(Integer.parseInt(cantidadString));

        return entradaRepository.save(entrada);
    }
}
