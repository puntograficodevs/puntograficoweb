package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Otro;
import com.puntografico.puntografico.domain.TipoColorOtro;
import com.puntografico.puntografico.repository.OtroRepository;
import com.puntografico.puntografico.repository.TipoColorOtroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class OtroService {

    private final OtroRepository otroRepository;

    private final TipoColorOtroRepository tipoColorOtroRepository;

    public Otro crear(HttpServletRequest request) {
        String tipoColorOtroString = request.getParameter("tipoColorOtro.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(tipoColorOtroString, "tipoColorOtroString es un dato obligatorio.");
        Assert.notNull(cantidadString, "cantidadString es un dato obligatorio.");

        TipoColorOtro tipoColorOtro = tipoColorOtroRepository.findById(Long.parseLong(tipoColorOtroString)).get();
        int cantidad = Integer.parseInt(cantidadString);

        Otro otro = new Otro();
        otro.setMedida(request.getParameter("medida"));
        otro.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        otro.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        otro.setInformacionAdicional(request.getParameter("informacionAdicional"));
        otro.setTipoColorOtro(tipoColorOtro);
        otro.setCantidad(cantidad);

        return otroRepository.save(otro);
    }
}
