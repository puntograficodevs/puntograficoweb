package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Rotulacion;
import com.puntografico.puntografico.domain.TipoCorteRotulacion;
import com.puntografico.puntografico.domain.TipoRotulacion;
import com.puntografico.puntografico.repository.RotulacionRepository;
import com.puntografico.puntografico.repository.TipoCorteRotulacionRepository;
import com.puntografico.puntografico.repository.TipoRotulacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional
public class RotulacionService {

    @Autowired
    private RotulacionRepository rotulacionRepository;

    @Autowired
    private TipoRotulacionRepository tipoRotulacionRepository;

    @Autowired
    private TipoCorteRotulacionRepository tipoCorteRotulacionRepository;

    public Rotulacion crear(HttpServletRequest request) {
        String medida = request.getParameter("medida");
        String cantidad = request.getParameter("cantidad");
        String horarioRotulacion = request.getParameter("horarioRotulacion");
        String direccionRotulacion = request.getParameter("direccionRotulacion");
        String tipoRotulacionString = request.getParameter("tipoRotulacion.id");
        String tipoCorteRotulacionString = request.getParameter("tipoCorteRotulacion.id");

        Assert.notNull(medida, "La medida es un dato obligatorio.");
        Assert.notNull(cantidad, "La cantidad es un dato obligatorio.");
        Assert.notNull(horarioRotulacion, "El horario de rotulación es un dato obligatorio.");
        Assert.notNull(direccionRotulacion, "La dirección de rotulación es un dato obligatorio.");
        Assert.notNull(tipoCorteRotulacionString, "El tipo de corte es un dato obligatorio.");
        Assert.notNull(tipoRotulacionString, "El tipo de rotulación es un dato obligatorio.");

        TipoRotulacion tipoRotulacion = tipoRotulacionRepository.findById(Long.parseLong(tipoRotulacionString)).get();
        TipoCorteRotulacion tipoCorteRotulacion = tipoCorteRotulacionRepository.findById(Long.parseLong(tipoCorteRotulacionString)).get();

        Rotulacion rotulacion = new Rotulacion();
        rotulacion.setEsLaminado(request.getParameter("esLaminado") != null);
        rotulacion.setHorarioRotulacion(horarioRotulacion);
        rotulacion.setDireccionRotulacion(direccionRotulacion);
        rotulacion.setMedida(medida);
        rotulacion.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        rotulacion.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        rotulacion.setInformacionAdicional(request.getParameter("informacionAdicional"));
        rotulacion.setCantidad(Integer.parseInt(cantidad));
        rotulacion.setTipoRotulacion(tipoRotulacion);
        rotulacion.setTipoCorteRotulacion(tipoCorteRotulacion);

        return rotulacionRepository.save(rotulacion);
    }
}
