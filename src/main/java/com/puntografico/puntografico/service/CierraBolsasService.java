package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadCierraBolsas;
import com.puntografico.puntografico.domain.CierraBolsas;
import com.puntografico.puntografico.domain.MedidaCierraBolsas;
import com.puntografico.puntografico.domain.TipoTroqueladoCierraBolsas;
import com.puntografico.puntografico.repository.CantidadCierraBolsasRepository;
import com.puntografico.puntografico.repository.CierraBolsasRepository;
import com.puntografico.puntografico.repository.MedidaCierraBolsasRepository;
import com.puntografico.puntografico.repository.TipoTroqueladoCierraBolsasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional
public class CierraBolsasService {

    @Autowired
    private TipoTroqueladoCierraBolsasRepository tipoTroqueladoCierraBolsasRepository;

    @Autowired
    private MedidaCierraBolsasRepository medidaCierraBolsasRepository;

    @Autowired
    private CantidadCierraBolsasRepository cantidadCierraBolsasRepository;

    @Autowired
    private CierraBolsasRepository cierraBolsasRepository;

    public CierraBolsas crear(HttpServletRequest request) {
        String tipoTroquelado = request.getParameter("tipoTroqueladoCierraBolsas.id");
        String medida = request.getParameter("medidaCierraBolsas.id");
        String opcionCantidad = request.getParameter("cantidadCierraBolsas.id");
        String cantidad = request.getParameter("cantidad");

        Assert.notNull(tipoTroquelado, "El tipo de troquelado es un dato obligatorio.");
        Assert.notNull(medida, "La medida es un dato obligatorio.");
        Assert.notNull(opcionCantidad, "La opción de cantidad es un dato obligatorio.");
        Assert.notNull(cantidad, "La cantidad es un dato obligatorio.");

        CierraBolsas cierraBolsas = new CierraBolsas();
        TipoTroqueladoCierraBolsas tipoTroqueladoCierraBolsas = tipoTroqueladoCierraBolsasRepository.findById(Long.parseLong(tipoTroquelado)).get();
        MedidaCierraBolsas medidaCierraBolsas = medidaCierraBolsasRepository.findById(Long.parseLong(medida)).get();
        CantidadCierraBolsas cantidadCierraBolsas = cantidadCierraBolsasRepository.findById(Long.parseLong(opcionCantidad)).get();

        cierraBolsas.setTipoTroqueladoCierraBolsas(tipoTroqueladoCierraBolsas);
        cierraBolsas.setMedidaCierraBolsas(medidaCierraBolsas);
        cierraBolsas.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        cierraBolsas.setCantidadCierraBolsas(cantidadCierraBolsas);
        cierraBolsas.setCantidad(Integer.parseInt(cantidad));
        cierraBolsas.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        cierraBolsas.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        cierraBolsas.setInformacionAdicional(request.getParameter("informacionAdicional"));

        return cierraBolsasRepository.save(cierraBolsas);
    }
}
