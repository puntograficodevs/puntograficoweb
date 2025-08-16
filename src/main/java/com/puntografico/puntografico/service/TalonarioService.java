package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional
public class TalonarioService {

    @Autowired
    private TipoTalonarioRepository tipoTalonarioRepository;

    @Autowired
    private TipoTroqueladoTalonarioRepository tipoTroqueladoTalonarioRepository;

    @Autowired
    private ModoTalonarioRepository modoTalonarioRepository;

    @Autowired
    private TipoColorTalonarioRepository tipoColorTalonarioRepository;

    @Autowired
    private MedidaTalonarioRepository medidaTalonarioRepository;

    @Autowired
    private TipoPapelTalonarioRepository tipoPapelTalonarioRepository;

    @Autowired
    private CantidadTalonarioRepository cantidadTalonarioRepository;

    @Autowired
    private TalonarioRepository talonarioRepository;

    public Talonario crear(HttpServletRequest request) {
        String tipoTalonarioString = request.getParameter("tipoTalonario.id");
        String tipoTroqueladoTalonarioString = request.getParameter("tipoTroqueladoTalonario.id");
        String modoTalonarioString = request.getParameter("modoTalonario.id");
        String tipoColorTalonarioString = request.getParameter("tipoColorTalonario.id");
        String medidaTalonarioString = request.getParameter("medidaTalonario.id");
        String tipoPapelTalonarioString = request.getParameter("tipoPapelTalonario.id");
        String cantidadTalonarioString = request.getParameter("cantidadTalonario.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(tipoTalonarioString, "tipoTalonarioString es un dato obligatorio.");
        Assert.notNull(tipoTroqueladoTalonarioString, "tipoTroqueladoTalonarioString es un dato obligatorio.");
        Assert.notNull(modoTalonarioString, "modoTalonarioString es un dato obligatorio.");
        Assert.notNull(tipoColorTalonarioString, "tipoColorTalonarioString es un dato obligatorio.");
        Assert.notNull(medidaTalonarioString, "medidaTalonarioString es un dato obligatorio.");
        Assert.notNull(tipoPapelTalonarioString, "tipoPapelTalonarioString es un dato obligatorio.");
        Assert.notNull(cantidadTalonarioString, " es un dato obligatorio.");

        TipoTalonario tipoTalonario = tipoTalonarioRepository.findById(Long.parseLong(tipoTalonarioString)).get();
        TipoTroqueladoTalonario tipoTroqueladoTalonario = tipoTroqueladoTalonarioRepository.findById(Long.parseLong(tipoTroqueladoTalonarioString)).get();
        ModoTalonario modoTalonario = modoTalonarioRepository.findById(Long.parseLong(modoTalonarioString)).get();
        TipoColorTalonario tipoColorTalonario = tipoColorTalonarioRepository.findById(Long.parseLong(tipoColorTalonarioString)).get();
        MedidaTalonario medidaTalonario = medidaTalonarioRepository.findById(Long.parseLong(medidaTalonarioString)).get();
        TipoPapelTalonario tipoPapelTalonario = tipoPapelTalonarioRepository.findById(Long.parseLong(tipoPapelTalonarioString)).get();
        CantidadTalonario cantidadTalonario = cantidadTalonarioRepository.findById(Long.parseLong(cantidadTalonarioString)).get();

        int cantidad;

        if (cantidadTalonario.getId() != 5) {
            cantidad = Integer.parseInt(cantidadTalonario.getCantidad());
        } else {
            cantidad = Integer.parseInt(cantidadString);
        }

        Talonario talonario = new Talonario();
        talonario.setConNumerado(request.getParameter("conNumerado") != null);
        talonario.setCantidadHojas(Integer.parseInt(request.getParameter("cantidadHojas")));
        talonario.setDetalleNumerado(request.getParameter("detalleNumerado"));
        talonario.setEsEncolado(request.getParameter("esEncolado") != null);
        talonario.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        talonario.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        talonario.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        talonario.setInformacionAdicional(request.getParameter("informacionAdicional"));
        talonario.setTipoTalonario(tipoTalonario);
        talonario.setTipoTroqueladoTalonario(tipoTroqueladoTalonario);
        talonario.setModoTalonario(modoTalonario);
        talonario.setTipoColorTalonario(tipoColorTalonario);
        talonario.setMedidaTalonario(medidaTalonario);
        talonario.setTipoPapelTalonario(tipoPapelTalonario);
        talonario.setCantidadTalonario(cantidadTalonario);
        talonario.setCantidad(cantidad);

        return talonarioRepository.save(talonario);
    }
}
